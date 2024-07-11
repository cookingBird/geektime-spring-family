package geektime.spring.data.errorcodedemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class ErrorCodeDemoApplication implements EnvironmentAware {
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ErrorCodeDemoApplication.class, args);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        log.info("environment: {}", environment);
    }
}

