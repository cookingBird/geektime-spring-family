package com.example.autoconfiguredemo;

import com.example.greeting.GreetingApplication;
import com.example.greeting2autoconfigurebackport.GreetingAutoconfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = {"com.example.greeting2autoconfigurebackport"})
public class AutoconfigureDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoconfigureDemoApplication.class, args);
    }

//    @Bean
//    public GreetingApplication greetingApplication(){
//        return new GreetingApplication(" spring");
//    }
}
