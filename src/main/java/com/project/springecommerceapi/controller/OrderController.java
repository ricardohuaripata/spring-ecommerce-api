package com.project.springecommerceapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.dto.OrderDto;
import com.project.springecommerceapi.response.OrderResponse;
import com.project.springecommerceapi.service.impl.OrderServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.util.UUID;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping("/{orderId}")
    @ApiOperation(value = "Get Order by ID", notes = "Get details of a specific order by its ID")
    public ResponseEntity<?> getOrder(
            @ApiParam(value = "ID of the order", required = true) @PathVariable("orderId") UUID orderId) {
        OrderResponse orderResponse = orderService.getOrder(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "Create Order", notes = "Create a new order")
    public ResponseEntity<?> createOrder(
            @ApiParam(value = "Order details", required = true) @RequestBody @Valid OrderDto orderDto) {
        OrderResponse createdOrder = orderService.createOrder(orderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

}
