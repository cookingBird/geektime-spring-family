package com.example.cachewithredisdemo2.repository;


import com.example.cachewithredisdemo2.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
