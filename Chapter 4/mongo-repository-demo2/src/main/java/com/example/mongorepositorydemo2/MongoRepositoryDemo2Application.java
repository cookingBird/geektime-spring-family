package com.example.mongorepositorydemo2;

import com.example.mongorepositorydemo2.converter.MoneyReadConverter;
import com.example.mongorepositorydemo2.model.Coffee;
import com.example.mongorepositorydemo2.repository.CoffeeRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Slf4j
@SpringBootApplication
@EnableMongoRepositories
public class MongoRepositoryDemo2Application implements CommandLineRunner {
    @Resource
    @Lazy
    private CoffeeRepository coffeeRepository;

    @Resource
    @Lazy
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MongoRepositoryDemo2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffee latte = Coffee.builder()
                .name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 30.0))
                .createTime(new Date())
                .updateTime(new Date()).build();

        coffeeRepository.insert(Arrays.asList(espresso, latte));
        coffeeRepository.findAll(Sort.by("name"))
                .forEach(c -> log.info("Saved Coffee {}", c));

        Thread.sleep(1000);
        latte.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
        latte.setUpdateTime(new Date());
        coffeeRepository.save(latte);
        coffeeRepository.findByName("latte")
                .forEach(c -> log.info("Coffee {}", c));
        UpdateResult updateResult = mongoTemplate.updateFirst(
                Query.query(Criteria.where("name").is("espresso")),
                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30))
                        .currentDate("updateTime"),
                Coffee.class);
        log.info("update result: {}", updateResult.getModifiedCount());
        Coffee updated = mongoTemplate.findById(espresso.getId(), Coffee.class);
        log.info("update result: {}", updated);

        coffeeRepository.deleteAll();
    }

    @Bean
    MongoCustomConversions conversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }
}
