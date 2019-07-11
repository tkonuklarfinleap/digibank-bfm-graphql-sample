package solutions.infinitec.digibank.api.rest;

import brave.Tracer;
import org.springframework.http.ResponseEntity;
import solutions.infinitec.digibank.api.dto.ErrorDetailDto;
import solutions.infinitec.digibank.api.dto.ErrorResponse;
import solutions.infinitec.digibank.api.dto.ImmutableErrorDetailDto;
import solutions.infinitec.digibank.api.dto.ImmutableErrorResponse;
import solutions.infinitec.digibank.domain.Error;
import solutions.infinitec.digibank.domain.ErrorDetail;

public abstract class CommonController {

  static final String API_V1 = "/api/v1";

  private final Tracer tracer;

  protected CommonController(final Tracer tracer) {
    this.tracer = tracer;
  }

  protected ResponseEntity<ErrorResponse> toErrorResponse(final Error error) {
    final String traceId = tracer.currentSpan().context().traceIdString();
    final ImmutableErrorResponse body = ImmutableErrorResponse.builder()
      .type(error.type())
      .status(error.status())
      .code(error.code())
      .arguments(error.arguments())
      .message(error.message())
      .traceId(traceId)
      .errorDetails(error.errorDetails().map(CommonController::mapErrorDetail))
      .build();
    return ResponseEntity.status(error.status()).body(body);
  }

  private static ErrorDetailDto mapErrorDetail(final ErrorDetail errorDetail) {
    return ImmutableErrorDetailDto.builder()
      .code(errorDetail.code())
      .message(errorDetail.message())
      .arguments(errorDetail.arguments())
      .build();
  }
}
