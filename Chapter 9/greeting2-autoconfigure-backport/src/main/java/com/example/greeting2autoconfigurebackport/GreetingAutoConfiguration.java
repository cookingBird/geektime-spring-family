package com.example.greeting2autoconfigurebackport;

import lombok.extern.slf4j.Slf4j;
import com.example.greeting.GreetingApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GreetingAutoConfiguration {
    static {
        log.info("GreetingAutoConfiguration-------------------");
    }
    @Bean
    public Greeting2AutoconfigurePostProcessor greeting2AutoconfigurePostProcessor(){
        return new Greeting2AutoconfigurePostProcessor();
    }
}
