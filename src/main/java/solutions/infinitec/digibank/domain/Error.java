package solutions.infinitec.digibank.domain;

import io.vavr.collection.List;
import org.immutables.value.Value;

@Value.Immutable
public interface Error {

  ErrorCodeType type();

  String code();

  List<String> arguments();

  String message();

  @Value.Derived
  default int status() {
    return type().statusCode();
  }

  List<ErrorDetail> errorDetails();
}
