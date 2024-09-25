package com.example.mynetflixeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MyNetflixEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyNetflixEurekaServerApplication.class, args);
    }

}
