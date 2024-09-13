package com.example.greetingspringbootautoconfigure;

import com.example.greeting.GreetingApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(GreetingApplication.class)
public class GreetingSpringBootAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(GreetingApplication.class)
    @ConditionalOnProperty(name = "greeting.enable", havingValue = "true", matchIfMissing = true)
    public GreetingApplication greetingApplication() {
        return new GreetingApplication();
    }
}
