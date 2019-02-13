package solutions.infinitec.nova.domain;

import org.immutables.value.Value;

import static org.immutables.value.Value.Style.ImplementationVisibility.PACKAGE;

@Value.Immutable
@Value.Style(visibility = PACKAGE)
public abstract class ErrorException extends RuntimeException {

  /**
   * Returns an exception carrying given error.
   */
  public static ErrorException of(final Error error) {
    return ImmutableErrorException.builder()
      .error(error)
      .build();
  }

  public abstract Error error();

  @Override
  public abstract String toString();

  @Override
  public String getMessage() {
    return error().message();
  }
}
