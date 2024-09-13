package com.example.greeting2backportspringbootautoconfigure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Greeting2BackportSpringBootAutoconfigure {

    static {
        log.info("GreetingAutoConfiguration-------------------");
    }
    @Bean
    public Greeting2AutoconfigurePostProcessor greeting2AutoconfigurePostProcessor(){
        return new Greeting2AutoconfigurePostProcessor();
    }
}
