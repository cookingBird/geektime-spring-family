package com.example.jedisdemo2.service;

import com.example.jedisdemo2.model.Coffee;
import com.example.jedisdemo2.model.CoffeeOrder;
import com.example.jedisdemo2.model.OrderState;
import com.example.jedisdemo2.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
@Transactional
public class CoffeeOrderService {
    @Resource
    private CoffeeOrderRepository coffeeOrderRepository;

    public CoffeeOrder createOrder(String customer, Coffee... coffees) {
        CoffeeOrder order = CoffeeOrder.builder()
                .customer(customer)
                .items(new ArrayList<>(Arrays.asList(coffees)))
                .state(OrderState.INIT)
                .build();
        CoffeeOrder saved = coffeeOrderRepository.save(order);
        if (log.isInfoEnabled()) {
            log.info("New Order: {}", saved);
        }
        return saved;
    }

    public boolean updateState(CoffeeOrder order, OrderState state) {
        if (state.compareTo(order.getState()) <= 0) {
            if (log.isWarnEnabled()) {
                log.warn("Wrong State order: {}, {}", state, order.getState());
            }
            return false;
        }
        else {
            order.setState(state);
            coffeeOrderRepository.save(order);
            if (log.isInfoEnabled()) {
                log.info("Updated Order: {}", order);
            }
            return true;
        }
    }
}
