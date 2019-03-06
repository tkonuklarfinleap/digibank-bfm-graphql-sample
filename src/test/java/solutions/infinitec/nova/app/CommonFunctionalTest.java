package solutions.infinitec.nova.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static solutions.infinitec.nova.app.IntegrationTest.INTEGRATION_TEST_TAG;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
  webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(properties = {
  "eureka.client.enabled=false",
})
@Tag(INTEGRATION_TEST_TAG)
public abstract class CommonFunctionalTest {

  @LocalServerPort
  private int serverPort;

  @Autowired
  private ApplicationEventPublisher eventPublisher;

  @BeforeAll
  public static void before() {
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @AfterEach
  public void afterEach() {
    eventPublisher.publishEvent(new TestMethodExecutedEvent());
  }

  public RequestSpecification requestSpecification() {
    return RestAssured
      .given()
      .log().all()
      .port(serverPort)
      .accept(ContentType.JSON)
      .contentType(ContentType.JSON);
  }
}
