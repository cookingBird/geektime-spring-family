package com.example.redisrepositorydemo2.repository;

import com.example.redisrepositorydemo2.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
