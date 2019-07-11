package solutions.infinitec.digibank.resolvers.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableLoginInput.class)
@JsonDeserialize(as = ImmutableLoginInput.class)
public interface LoginInput {
  String username();

  String password();
}
