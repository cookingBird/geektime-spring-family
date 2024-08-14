package com.example.cachedemo2;

import com.example.cachedemo2.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableCaching(proxyTargetClass = true)
public class CacheDemo2Application implements ApplicationRunner {
    @Resource
    @Lazy
    private CoffeeService coffeeService;

    public static void main(String[] args) {
        SpringApplication.run(CacheDemo2Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Count: {}", coffeeService.findAllCoffee().size());
        for (int i = 0; i < 10; i++) {
            log.info("Reading from cache.");
            coffeeService.findAllCoffee();
        }
        coffeeService.reloadCoffee();
        log.info("Reading after refresh.");
        coffeeService.findAllCoffee().forEach(c -> log.info("Coffee {}", c.getName()));
    }
}
