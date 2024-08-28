package com.example.waiterservice.controller;


import com.example.waiterservice.controller.request.NewOrderRequest;
import com.example.waiterservice.model.Coffee;
import com.example.waiterservice.model.CoffeeOrder;
import com.example.waiterservice.service.CoffeeOrderService;
import com.example.waiterservice.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/order")
@Slf4j
public class CoffeeOrderController {
    @Resource
    private CoffeeOrderService orderService;
    @Resource
    private CoffeeService coffeeService;

    @GetMapping("/{id}")
    public CoffeeOrder getOrder(@PathVariable("id") Long id) {
        return orderService.get(id);
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CoffeeOrder create(@RequestBody NewOrderRequest newOrder) {
        log.info("Receive new Order {}", newOrder);
        Coffee[] coffeeList = coffeeService.getCoffeeByName(newOrder.getItems())
                .toArray(new Coffee[] {});
        return orderService.createOrder(newOrder.getCustomer(), coffeeList);
    }
}
