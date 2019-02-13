package solutions.infinitec.nova.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vavr.collection.List;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableErrorDetailDto.class)
@JsonDeserialize(as = ImmutableErrorDetailDto.class)
public interface ErrorDetailDto {

  @JsonProperty("code")
  String code();

  @JsonProperty("arguments")
  List<String> arguments();

  @JsonProperty("message")
  String message();
}
