package solutions.infinitec.digibank.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import solutions.infinitec.digibank.operations.business.NovaBusinessClient;
import solutions.infinitec.digibank.operations.business.dto.ImmutableLoginRequest;
import solutions.infinitec.digibank.operations.business.dto.LoginResponse;
import solutions.infinitec.digibank.resolvers.dto.LoginInput;

@Component
public class Mutation implements GraphQLMutationResolver {

  private final NovaBusinessClient client;

  Mutation(final NovaBusinessClient client) {
    this.client = client;
  }

  public LoginResponse login(final LoginInput input) {
    return client.login(ImmutableLoginRequest.builder()
      .username(input.username())
      .password(input.password())
      .build()
    )
      .getOrElseThrow(() -> new RuntimeException("Boomm"));
  }
}
