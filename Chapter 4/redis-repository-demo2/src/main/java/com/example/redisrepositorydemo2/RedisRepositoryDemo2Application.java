package com.example.redisrepositorydemo2;

import com.example.redisrepositorydemo2.converter.BytesToMoneyConverter;
import com.example.redisrepositorydemo2.converter.MoneyToBytesConverter;
import com.example.redisrepositorydemo2.model.Coffee;
import com.example.redisrepositorydemo2.service.CoffeeService;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableRedisRepositories
public class RedisRepositoryDemo2Application implements CommandLineRunner {

    @Resource
    private CoffeeService coffeeService;

    public static void main(String[] args) {
        SpringApplication.run(RedisRepositoryDemo2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<Coffee> c = coffeeService.findSimpleCoffeeFromCache("mocha");
        log.info("Coffee {}", c);

        for (int i = 0; i < 5; i++) {
            c = coffeeService.findSimpleCoffeeFromCache("mocha");
        }

        log.info("Value from Redis: {}", c);
    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer() {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

    @Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(
                Arrays.asList(new MoneyToBytesConverter(), new BytesToMoneyConverter())
        );
    }
}
