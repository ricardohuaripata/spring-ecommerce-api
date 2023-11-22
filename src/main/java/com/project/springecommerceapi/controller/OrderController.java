package com.project.springecommerceapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.dto.CartItemDto;
import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.dto.ShippingAddressDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.Order;
import com.project.springecommerceapi.entity.User;
import com.project.springecommerceapi.service.impl.CartServiceImpl;
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
        Order orderToFind = orderService.getOrderById(orderId);
        return new ResponseEntity<>(orderToFind, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createOrder(Authentication authentication, @RequestBody @Valid OrderDto orderDto) {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
        Order createdOrder = orderService.createOrder(user, orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

}
