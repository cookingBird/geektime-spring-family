package com.example.redisrepositorydemo2.repository;

import com.example.redisrepositorydemo2.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
