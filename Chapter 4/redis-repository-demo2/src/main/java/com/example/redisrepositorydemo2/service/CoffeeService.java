package com.example.redisrepositorydemo2.service;

import com.example.redisrepositorydemo2.model.Coffee;
import com.example.redisrepositorydemo2.model.CoffeeCache;
import com.example.redisrepositorydemo2.repository.CoffeeCacheRepository;
import com.example.redisrepositorydemo2.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
@Slf4j
public class CoffeeService {
    @Resource
    private CoffeeRepository coffeeRepository;

    @Resource
    private CoffeeCacheRepository coffeeCacheRepository;

    public List<Coffee> findAll() {
        return coffeeRepository.findAll();
    }

    public Optional<Coffee> findSimpleCoffeeFromCache(String name) {
        Optional<CoffeeCache> cache = coffeeCacheRepository.findOneByName(name);
        if (cache.isPresent()) {
            CoffeeCache coffeeCache = cache.get();
            Coffee coffee = Coffee.builder()
                    .name(coffeeCache.getName())
                    .price(coffeeCache.getPrice())
                    .build();
            log.info("Coffee {} found in cache.", coffeeCache);
            return Optional.of(coffee);
        } else {
            Optional<Coffee> coffee = this.findOneCoffee(name);
            coffee.ifPresent(c -> {
                CoffeeCache coffeeCache = CoffeeCache.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .price(c.getPrice())
                        .build();
                log.info("Save Coffee {} to cache.", coffeeCache);
                coffeeCacheRepository.save(coffeeCache);
            });
            return coffee;
        }
    }

    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(
                Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Coffee Found: {}", coffee);
        return coffee;
    }
}
