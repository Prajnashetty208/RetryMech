package com.order.rest.service;

import com.order.rest.model.Order;

import java.io.IOException;
import java.util.Set;

public interface OrderService {

    Order save(Order obj) throws IOException;
    Set<Order> findAll();
    Order findById(Long id);
    void deleteOrder(Long id);
}
