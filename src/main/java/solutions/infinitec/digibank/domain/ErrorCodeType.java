package solutions.infinitec.digibank.domain;

import org.springframework.http.HttpStatus;

public enum ErrorCodeType {

  BAD_REQUEST(HttpStatus.BAD_REQUEST),
  UNAUTHORIZED(HttpStatus.UNAUTHORIZED),
  FORBIDDEN(HttpStatus.FORBIDDEN),
  NOT_FOUND(HttpStatus.NOT_FOUND),
  CONFLICT(HttpStatus.CONFLICT),
  INTERNAL(HttpStatus.INTERNAL_SERVER_ERROR),
  METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED),
  ;

  final HttpStatus status;

  ErrorCodeType(final HttpStatus status) {
    this.status = status;
  }

  public int statusCode() {
    return status.value();
  }
}
