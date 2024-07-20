package com.example.demo.demos.web;

import com.example.demo.demos.web.mapper.CoffeeMapper;
import com.example.demo.demos.web.model.Coffee;
import com.example.demo.demos.web.model.CoffeeOrder;
import com.example.demo.demos.web.model.OrderState;
import com.example.demo.demos.web.repository.CoffeeOrderRepository;
import com.example.demo.demos.web.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories
@MapperScan("com.example.demo.demos.web.mapper")
@Slf4j
public class JpaDemoApplication implements CommandLineRunner {
    @Resource
    CoffeeRepository coffeeRepository;

    @Resource
    CoffeeOrderRepository coffeeOrderRepository;

    @Resource
    CoffeeMapper coffeeMapper;


    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        this.jpaTest();
        this.mapperTest();
    }

    public void jpaTest() {

        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .build();
        coffeeRepository.save(espresso);

        log.info("Coffee: {}", espresso);

        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30))
                .build();
        coffeeRepository.save(latte);
        if (log.isInfoEnabled()) {
            log.info("latte: {}", latte);
        }


        CoffeeOrder order = CoffeeOrder.builder()
                .customer("Li Lei")
                .state(OrderState.INIT)
                .items(Collections.singletonList(latte))
                .build();
        coffeeOrderRepository.save(order);

        if (log.isInfoEnabled()) {
            log.info("order: {}", order);
        }

        order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(latte, espresso))
                .state(OrderState.INIT)
                .build();

        coffeeOrderRepository.save(order);

        if (log.isInfoEnabled()) {
            log.info("order: {}", order);
        }
    }

    public void mapperTest() {
        Coffee c = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 35))
                .build();
        int save = coffeeMapper.save(c);
        log.info("Save {} Coffee : {}", save, c);
        c = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20))
                .build();
        save = coffeeMapper.save(c);
        log.info("Save {} Coffee : {}", save, c);

        c = coffeeMapper.findById(c.getId());
        log.info("Find Coffee: {}", c);
    }
}
