package solutions.infinitec.nova.domain;

import org.immutables.value.Value;

@Value.Immutable
public interface Error {

  @Value.Parameter
  String name();

  @Value.Parameter
  String message();

  @Value.Parameter
  String[] params();
}
