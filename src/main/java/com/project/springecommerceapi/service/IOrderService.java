package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.response.OrderResponse;
import com.stripe.exception.StripeException;

public interface IOrderService {
    OrderResponse getOrder(UUID orderId);

    OrderResponse createOrder(User user, OrderDto orderDto) throws StripeException;
    
}
