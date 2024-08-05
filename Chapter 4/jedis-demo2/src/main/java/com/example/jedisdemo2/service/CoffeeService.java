package com.example.jedisdemo2.service;

import com.example.jedisdemo2.model.Coffee;
import com.example.jedisdemo2.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CoffeeService {

    @Resource
    private CoffeeRepository coffeeRepository;

    public List<Coffee> findAllCoffee() {
        return this.coffeeRepository.findAll();
    }

    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(Example.of(Coffee.builder().name(name).build(),
                matcher
        ));
        return coffee;
    }
}
