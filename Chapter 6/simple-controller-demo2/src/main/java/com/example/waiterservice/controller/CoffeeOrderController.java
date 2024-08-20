package com.example.waiterservice.controller;


import com.example.waiterservice.controller.request.NewOrderRequest;
import com.example.waiterservice.model.Coffee;
import com.example.waiterservice.model.CoffeeOrder;
import com.example.waiterservice.service.CoffeeOrderService;
import com.example.waiterservice.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Resource
    private CoffeeOrderService orderService;
    @Resource
    private CoffeeService coffeeService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[]{});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<CoffeeOrder> getAll() {
        return orderService.getAll();
    }
}
