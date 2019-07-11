package solutions.infinitec.digibank.domain;

import io.vavr.collection.List;
import io.vavr.control.Option;

import java.text.MessageFormat;
import java.util.Objects;

import static io.vavr.API.List;

public enum ErrorCode {

  // general errors
  INVALID_REQUEST(ErrorCodeType.BAD_REQUEST, "Bad request"),
  AUTHENTICATION_FAILED(ErrorCodeType.UNAUTHORIZED, "Authentication failed"),
  AUTHORIZATION_FAILED(ErrorCodeType.FORBIDDEN, "Authorization failed"),
  RESOURCE_NOT_FOUND(ErrorCodeType.NOT_FOUND, "Unknown endpoint"),
  CONFLICT(ErrorCodeType.CONFLICT, "Conflict"),
  INTERNAL_ERROR(ErrorCodeType.INTERNAL, "Internal server error"),
  METHOD_NOT_ALLOWED(ErrorCodeType.METHOD_NOT_ALLOWED, "Method is not allowed for this resource");

  final ErrorCodeType type;
  private final String message;

  ErrorCode(final ErrorCodeType type, final String message) {
    this.type = type;
    this.message = message;
  }

  static final List<ErrorCode> BASIC_ERROR_CODES = List(
    INVALID_REQUEST,
    AUTHENTICATION_FAILED,
    AUTHORIZATION_FAILED,
    RESOURCE_NOT_FOUND,
    CONFLICT,
    INTERNAL_ERROR,
    METHOD_NOT_ALLOWED
  );

  public static Option<ErrorCode> basicErrorCodeForStatusCode(final int statusCode) {
    return BASIC_ERROR_CODES.find(e -> e.type.statusCode() == statusCode);
  }

  public Error asError(final Object... params) {
    return ImmutableError.builder()
      .type(type)
      .code(name())
      .arguments(List.of(params).map(Objects::toString))
      .message(MessageFormat.format(message, params))
      .errorDetails(List.empty())
      .build();
  }
}
