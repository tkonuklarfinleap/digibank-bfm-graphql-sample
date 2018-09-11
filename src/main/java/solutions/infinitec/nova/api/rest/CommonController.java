package solutions.infinitec.nova.api.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import solutions.infinitec.nova.api.dto.ErrorResponse;
import solutions.infinitec.nova.api.dto.ImmutableErrorResponse;
import solutions.infinitec.nova.domain.Error;

public abstract class CommonController {

  static final String API_V1 = "/api/v1";

  protected static ResponseEntity<ErrorResponse> toErrorResponse(final Error error) {
    return toErrorResponse(error, HttpStatus.BAD_REQUEST);
  }

  static ResponseEntity<ErrorResponse> toErrorResponse(final Error error, final HttpStatus status) {
    return ResponseEntity.status(status)
      .body(ImmutableErrorResponse.builder()
        .name(error.name())
        .message(error.message())
        .params(error.params())
        .build());
  }
}
