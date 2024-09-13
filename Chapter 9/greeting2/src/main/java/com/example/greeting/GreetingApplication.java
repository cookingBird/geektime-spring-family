package com.example.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import javax.annotation.PostConstruct;

@Slf4j
public class GreetingApplication implements InitializingBean {
    private final String foo;

    public GreetingApplication() {
        this("World");
    }

    public GreetingApplication(String foo) {
        this.foo = foo;
    }


    @PostConstruct
    public void run() throws Exception {
        log.info("PostConstruct: Hello {}",this.foo);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("InitializingBean: Hello {}",this.foo);
    }
}
