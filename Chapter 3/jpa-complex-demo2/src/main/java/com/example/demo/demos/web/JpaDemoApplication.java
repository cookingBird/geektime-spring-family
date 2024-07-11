package com.example.demo.demos.web;

import com.example.demo.demos.web.model.Coffee;
import com.example.demo.demos.web.model.CoffeeOrder;
import com.example.demo.demos.web.model.OrderState;
import com.example.demo.demos.web.repository.CoffeeOrderRepository;
import com.example.demo.demos.web.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class JpaDemoApplication implements CommandLineRunner {
    @Resource
    CoffeeRepository coffeeRepository;

    @Resource
    CoffeeOrderRepository orderRepository;


    public static void main(String[] args) {
        SpringApplication.run(JpaDemoApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        initOrders();
        findOrder();
    }

    public void initOrders() {

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
        orderRepository.save(order);

        if (log.isInfoEnabled()) {
            log.info("order: {}", order);
        }

        order = CoffeeOrder.builder()
                .customer("Li Lei")
                .items(Arrays.asList(latte, espresso))
                .state(OrderState.INIT)
                .build();

        orderRepository.save(order);

        if (log.isInfoEnabled()) {
            log.info("order: {}", order);
        }
    }

    public void findOrder() {
        coffeeRepository.findAll()
                .forEach(coffee -> {
                    log.info("coffee: {}", coffee);
                });
        List<CoffeeOrder> list = orderRepository.findTop3ByOrderByUpdateTimeDesc();
        log.info("findTop3ByOrderByUpdateTimeDesc: {}", getJoinedId(list));

        list = orderRepository.findByCustomerOrderById("Li Lei");
        log.info("findByCustomerOrderById: {}", getJoinedId(list));

        // 不开启事务会因为没Session而报LazyInitializationException
        list.forEach(o -> {
            log.info("Order {}", o.getId());
            o.getItems().forEach(i -> log.info("  Item {}", i));
        });

        list = orderRepository.findByItems_Name("latte");
        log.info("findByItems_Name: {}", getJoinedId(list));
    }

    private String getJoinedId(List<CoffeeOrder> list) {
        return list.stream()
                .map(CoffeeOrder::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }
}
