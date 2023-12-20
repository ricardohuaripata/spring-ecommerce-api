package com.project.springecommerceapi.service;

import java.util.UUID;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.response.OrderResponse;
import com.stripe.exception.StripeException;

public interface IOrderService {
    Order getOrderById(UUID orderId);

    OrderResponse getAuthOrderResponseById(UUID orderId);

    OrderResponse createOrder(OrderDto orderDto) throws StripeException;

}
