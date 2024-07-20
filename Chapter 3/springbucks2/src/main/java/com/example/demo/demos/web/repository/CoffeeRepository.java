package com.example.demo.demos.web.repository;

import com.example.demo.demos.web.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
