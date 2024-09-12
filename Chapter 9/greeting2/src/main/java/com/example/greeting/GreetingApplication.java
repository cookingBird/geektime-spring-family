package com.example.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class GreetingApplication implements ApplicationRunner {
    private final String foo;

    public GreetingApplication() {
        this("World");
    }

    public GreetingApplication(String foo) {
        this.foo = foo;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Hello {}",this.foo);
    }
}
