package com.project.springecommerceapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springecommerceapi.dto.CartItemDto;
import com.project.springecommerceapi.entity.Cart;
import com.project.springecommerceapi.response.SuccessResponse;
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

    @PatchMapping("/{cartId}")
    public ResponseEntity<?> addToCart(@PathVariable("cartId") UUID cartId,
            @RequestBody @Valid CartItemDto cartItemDto) {
        Cart cart = cartService.createCartItem(cartId, cartItemDto);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> clearCart(@PathVariable("cartId") UUID cartId) {
        cartService.clearCart(cartId);
        SuccessResponse successResponse = SuccessResponse.builder()
                .type("Success")
                .message("Cart cleared")
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("cartItemId") UUID cartItemId) {
        Cart cart = cartService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteExpiredCarts() {
        int deletedCarts = cartService.deleteExpiredCarts();
        SuccessResponse successResponse = SuccessResponse.builder()
                .type("Success")
                .message("Deleted expired carts: " + deletedCarts)
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

}
