package com.example.greeting2autoconfigurebackport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class GreetingAutoconfiguration {
    @Bean
    public static Greeting2AutoconfigurePostProcessor greeting2AutoconfigurePostProcessor(){
        return new Greeting2AutoconfigurePostProcessor();
    }
}
