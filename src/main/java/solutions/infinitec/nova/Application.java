package solutions.infinitec.nova;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@Slf4j
public class Application {

  public static void main(final String[] args) {
    log.info("Initializing Spring Boot application");
    SpringApplication.run(Application.class, args);
  }
}
