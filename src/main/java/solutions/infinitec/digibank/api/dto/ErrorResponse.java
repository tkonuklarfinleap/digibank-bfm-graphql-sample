package solutions.infinitec.digibank.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.vavr.collection.List;
import org.immutables.value.Value;
import solutions.infinitec.digibank.domain.ErrorCodeType;

@Value.Immutable
@JsonSerialize(as = ImmutableErrorResponse.class)
@JsonDeserialize(as = ImmutableErrorResponse.class)
public interface ErrorResponse {

  @JsonProperty("type")
  ErrorCodeType type();

  @JsonProperty("status")
  int status();

  @JsonProperty("code")
  String code();

  @JsonProperty("arguments")
  List<String> arguments();

  @JsonProperty("message")
  String message();

  @JsonProperty("traceId")
  String traceId();

  @JsonProperty("errorDetails")
  List<ErrorDetailDto> errorDetails();
}
