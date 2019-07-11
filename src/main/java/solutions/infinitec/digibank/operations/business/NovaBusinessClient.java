package solutions.infinitec.digibank.operations.business;

import io.vavr.control.Validation;
import solutions.infinitec.digibank.domain.Error;
import solutions.infinitec.digibank.operations.business.dto.LoginRequest;
import solutions.infinitec.digibank.operations.business.dto.LoginResponse;

public interface NovaBusinessClient {
  Validation<Error, LoginResponse> login(final LoginRequest request);
}
