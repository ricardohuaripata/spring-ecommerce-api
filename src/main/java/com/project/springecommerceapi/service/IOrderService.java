package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.User;
import com.stripe.exception.StripeException;

public interface IOrderService {
    Order getOrderById(UUID orderId);

    Order createOrder(User user, OrderDto orderDto) throws StripeException;
    
}
