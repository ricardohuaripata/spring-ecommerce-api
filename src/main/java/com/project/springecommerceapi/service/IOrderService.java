package com.project.springecommerceapi.service;

import java.util.List;
import java.util.UUID;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.response.OrderResponse;
import com.stripe.exception.StripeException;

public interface IOrderService {

    OrderResponse getAuthOrderResponseById(UUID orderId);

    List<OrderResponse> getAuthOrders();

    OrderResponse createOrder(OrderDto orderDto) throws StripeException;

}
