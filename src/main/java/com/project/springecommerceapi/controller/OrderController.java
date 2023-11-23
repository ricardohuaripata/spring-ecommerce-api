package com.project.springecommerceapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.response.OrderResponse;
import com.project.springecommerceapi.service.impl.OrderServiceImpl;
import com.project.springecommerceapi.service.impl.UserServiceImpl;

import java.util.UUID;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;
    private final UserServiceImpl userService;

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable("orderId") UUID orderId) {
        OrderResponse orderResponse = orderService.getOrder(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(Authentication authentication, @RequestBody @Valid OrderDto orderDto) {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
        OrderResponse createdOrder = orderService.createOrder(user, orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

}
