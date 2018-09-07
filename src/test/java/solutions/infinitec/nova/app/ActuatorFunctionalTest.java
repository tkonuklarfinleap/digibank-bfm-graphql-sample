package solutions.infinitec.nova.app;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

class ActuatorFunctionalTest extends CommonFunctionalTest {

  @Test
  void thatHealthEndpointContainsCurrentServerStatus() {
    final ObjectNode root = requestSpecification()
      .when()
      .get("/actuator/health")
      .then()
      .log().all()
      .and()
      .assertThat()
      .statusCode(HttpStatus.OK.value())
      .and()
      .extract().as(ObjectNode.class);

    assertThat(root.get("status")).isInstanceOf(TextNode.class);
    assertThat(root.get("status").textValue()).isEqualTo("UP");
  }
}
