package com.example.cachedemo2.repository;

import com.example.cachedemo2.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
