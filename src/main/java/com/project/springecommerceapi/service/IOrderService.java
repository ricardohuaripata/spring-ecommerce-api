package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.User;

public interface IOrderService {
    Order getOrderById(UUID orderId);

    Order createOrder(User user, OrderDto orderDto);
    
}
