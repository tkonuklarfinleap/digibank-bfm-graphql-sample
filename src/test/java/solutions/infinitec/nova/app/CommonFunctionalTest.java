package solutions.infinitec.nova.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public abstract class CommonFunctionalTest {

  @LocalServerPort
  private int serverPort;

  @Before
  public void before() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  protected RequestSpecification requestSpecification() {
    return RestAssured
      .given()
      .log().all()
      .port(serverPort)
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON);
  }
}
