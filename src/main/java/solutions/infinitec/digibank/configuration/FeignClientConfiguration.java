package solutions.infinitec.digibank.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestInterceptor;
import feign.Response;
import feign.codec.ErrorDecoder;
import io.vavr.API;
import io.vavr.Tuple2;
import io.vavr.control.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StreamUtils;
import solutions.infinitec.digibank.api.InjectedHeader;
import solutions.infinitec.digibank.api.dto.ErrorDetailDto;
import solutions.infinitec.digibank.api.dto.ErrorResponse;
import solutions.infinitec.digibank.domain.Error;
import solutions.infinitec.digibank.domain.ErrorCode;
import solutions.infinitec.digibank.domain.ErrorDetail;
import solutions.infinitec.digibank.domain.ErrorException;
import solutions.infinitec.digibank.domain.ImmutableError;
import solutions.infinitec.digibank.domain.ImmutableErrorDetail;
import solutions.infinitec.digibank.domain.MdcSupport;

import java.util.function.Supplier;

import static io.vavr.API.Invalid;
import static io.vavr.API.Some;
import static io.vavr.API.Tuple;
import static io.vavr.API.Valid;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Configuration
public class FeignClientConfiguration {

  public static <T> Validation<Error, T> convertExceptions(final Supplier<T> supplier) {
    try {
      return Valid(supplier.get());
    } catch (final ErrorException e) {
      log.warn("Feign client responded with error", e);
      return Invalid(e.error());
    } catch (final Exception e) {
      log.warn("Feign client throws an error", e);
      return Invalid(ErrorCode.INTERNAL_ERROR.asError());
    }
  }

  @Bean
  public RequestInterceptor injectedHeadersRequestInterceptor() {
    return template -> {
      final var headersToForward = API.List(MdcSupport.values())
        .flatMap(mdc ->
          Some(mdc.get())
            .map(value -> Tuple(InjectedHeader.valueOf(mdc.name()).headerName(), value))
        );

      if (!headersToForward.isEmpty()) {
        headersToForward.forEach(h -> h.apply(template::header));
        log.debug("Feign request {} was intercepted by header interceptor. Injected headers: {}",
          template.url(), headersToForward.map(Tuple2::_1));
      }
    };
  }

  @Bean
  public ErrorDecoder errorDecoder(final ObjectMapper objectMapper) {
    return new CustomFeignErrorDecoder(objectMapper);
  }

  static class CustomFeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    CustomFeignErrorDecoder(final ObjectMapper objectMapper) {
      this.objectMapper = objectMapper;
    }

    @Override
    public Exception decode(final String methodKey, final Response response) {
      String payload = null;
      try (final var is = response.body().asInputStream()) {
        payload = StreamUtils.copyToString(is, UTF_8);
        return mapError(objectMapper.readValue(payload, ErrorResponse.class));
      } catch (final Exception ex) {
        log.error("Error while mapping error response from Feign client. Response from server: {}", payload, ex);
        return mapResponse(response);
      }
    }

    private Exception mapError(final ErrorResponse response) {
      log.debug("Error received from Feign client: {}", response);
      return ErrorException.of(map(response));
    }

    private Exception mapResponse(final Response response) {
      return ErrorException.of(
        ErrorCode.basicErrorCodeForStatusCode(response.status())
          .getOrElse(ErrorCode.INTERNAL_ERROR)
          .asError()
      );
    }

    private Error map(final ErrorResponse error) {
      return ImmutableError.builder()
        .type(error.type())
        .code(error.code())
        .message(error.message())
        .arguments(error.arguments())
        .errorDetails(error.errorDetails().map(this::map))
        .build();
    }

    private ErrorDetail map(final ErrorDetailDto errorDetail) {
      return ImmutableErrorDetail.builder()
        .code(errorDetail.code())
        .message(errorDetail.message())
        .arguments(errorDetail.arguments())
        .build();
    }
  }
}
