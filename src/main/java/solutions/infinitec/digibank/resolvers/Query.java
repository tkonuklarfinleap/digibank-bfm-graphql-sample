package solutions.infinitec.digibank.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

  public String sample() {
    return "Hello World!";
  }
}
