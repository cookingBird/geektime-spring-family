package com.example.myzkwaiterservice.service;

import com.example.myzkwaiterservice.model.Coffee;
import com.example.myzkwaiterservice.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    public Coffee saveCoffee(String name, Money price) {
        return coffeeRepository.save(Coffee.builder().name(name).price(price).build());
    }

    @Cacheable
    public List<Coffee> getAllCoffee() {
        return coffeeRepository.findAll(Sort.by("id"));
    }

    public Coffee getCoffee(Long id) {
//        return coffeeRepository.findById(id).get();
        return coffeeRepository.getOne(id);
    }

    public long getCoffeeCount() {
        return coffeeRepository.count();
    }

    public Coffee getCoffee(String name) {
        return coffeeRepository.findByName(name);
    }

    public List<Coffee> getCoffeeByName(List<String> names) {
        return coffeeRepository.findByNameInOrderById(names);
    }
}
