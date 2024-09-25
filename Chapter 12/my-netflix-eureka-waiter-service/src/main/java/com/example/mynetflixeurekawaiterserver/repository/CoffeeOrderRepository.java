package com.example.mynetflixeurekawaiterserver.repository;

import com.example.mynetflixeurekawaiterserver.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
