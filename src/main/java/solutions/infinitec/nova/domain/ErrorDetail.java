package solutions.infinitec.nova.domain;

import io.vavr.collection.List;
import org.immutables.value.Value;

@Value.Immutable
public interface ErrorDetail {

  String code();

  List<String> arguments();

  String message();
}
