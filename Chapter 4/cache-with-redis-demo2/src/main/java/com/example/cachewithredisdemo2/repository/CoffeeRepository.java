package com.example.cachewithredisdemo2.repository;


import com.example.cachewithredisdemo2.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
