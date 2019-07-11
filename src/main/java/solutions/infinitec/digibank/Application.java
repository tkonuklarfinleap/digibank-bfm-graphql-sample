package solutions.infinitec.digibank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@Slf4j
public class Application {

  public static void main(final String[] args) {
    log.info("Initializing Spring Boot application");
    SpringApplication.run(Application.class, args);
  }
}
