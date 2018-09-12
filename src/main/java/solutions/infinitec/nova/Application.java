package solutions.infinitec.nova;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class Application {

  private static final Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(final String[] args) {
    logger.info("Initialising Spring Boot application");
    SpringApplication.run(Application.class, args);
  }
}
