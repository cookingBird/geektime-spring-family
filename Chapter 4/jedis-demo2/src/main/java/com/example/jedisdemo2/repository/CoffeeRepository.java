package com.example.jedisdemo2.repository;

import com.example.jedisdemo2.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
