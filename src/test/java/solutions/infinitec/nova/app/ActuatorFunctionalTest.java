package solutions.infinitec.nova.app;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.hamcrest.CoreMatchers.is;

class ActuatorFunctionalTest extends CommonFunctionalTest {

  @Test
  void thatHealthEndpointContainsCurrentServerStatus() {
    requestSpecification()
      .when()
      .get("/actuator/health")
      .then()
      .log().all()
      .and()
      .assertThat()
      .statusCode(HttpStatus.OK.value())
      .body("status", is("UP"));
  }

  @Test
  void thatInfoEndpointContainsGitAndBuildInfo() {
    requestSpecification()
      .when()
      .get("/actuator/info")
      .then()
      .log().all()
      .and()
      .assertThat()
      .statusCode(HttpStatus.OK.value())

      .body("git.branch", is("feature/update-template"))
      .body("git.commit.time", is("2019-02-07T14:09:31Z"))
      .body("build.artifact", is("nova-template"))
      .body("build.name", is("nova-template"))
      .body("build.time", is("2019-01-30T18:12:40.981Z"))
      .body("build.version", is("0.0.1-SNAPSHOT"))
      .body("build.group", is("solutions.infinitec.nova"));
  }
}
