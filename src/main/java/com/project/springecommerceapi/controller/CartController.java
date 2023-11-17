package com.project.springecommerceapi.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.dto.ProductDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.entity.Product;
import com.project.springecommerceapi.service.impl.CartServiceImpl;

import java.util.UUID;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServiceImpl cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<?> getCart(@PathVariable("cartId") UUID cartId) {
        Cart cartToFind = cartService.getCartById(cartId);
        return new ResponseEntity<>(cartToFind, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> createCart() {
        Cart createdCart = cartService.createCart();
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

}
