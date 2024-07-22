package com.example.redisdemo2.repository;

import com.example.redisdemo2.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
