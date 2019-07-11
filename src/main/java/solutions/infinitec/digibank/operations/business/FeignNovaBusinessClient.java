package solutions.infinitec.digibank.operations.business;

import io.vavr.control.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import solutions.infinitec.digibank.configuration.FeignClientConfiguration;
import solutions.infinitec.digibank.domain.Error;
import solutions.infinitec.digibank.operations.business.dto.LoginRequest;
import solutions.infinitec.digibank.operations.business.dto.LoginResponse;

import static solutions.infinitec.digibank.configuration.FeignClientConfiguration.convertExceptions;

@Slf4j
@Component
public class FeignNovaBusinessClient implements NovaBusinessClient {

  private final BusinessFeignClient novaBusinessFeignClient;

  FeignNovaBusinessClient(final BusinessFeignClient novaBusinessFeignClient) {
    this.novaBusinessFeignClient = novaBusinessFeignClient;
  }

  @Override
  public Validation<Error, LoginResponse> login(final LoginRequest request) {
    return convertExceptions(() -> novaBusinessFeignClient.login(request));
  }

  @FeignClient(
    name = "nova-core",
    url = "${nova.business.url}",
    configuration = FeignClientConfiguration.class
  )
  public interface BusinessFeignClient {
    @PostMapping("/api/v1/login")
    LoginResponse login(@RequestBody final LoginRequest request);
  }
}
