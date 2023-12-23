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
import com.project.springecommerceapi.response.CartResponse;
import com.project.springecommerceapi.response.SuccessResponse;
import com.project.springecommerceapi.service.impl.CartServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
        CartResponse cartToFind = cartService.getCartResponseById(cartId);
        return new ResponseEntity<>(cartToFind, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "Create Cart", notes = "Create a new shopping cart")
    public ResponseEntity<?> createCart() {
        CartResponse createdCart = cartService.createCart();
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

    @PatchMapping("/{cartId}")
    @ApiOperation(value = "Add to Cart", notes = "Add an item to the shopping cart")
    public ResponseEntity<?> addToCart(
            @ApiParam(value = "ID of the cart", required = true) @PathVariable("cartId") UUID cartId,
            @ApiParam(value = "Product Variant to be added", required = true) @RequestBody @Valid CartItemDto cartItemDto) {
        CartResponse cart = cartService.addToCart(cartId, cartItemDto);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}/items")
    @ApiOperation(value = "Clear Cart", notes = "Clear all items from the cart")
    public ResponseEntity<?> clearCart(
            @ApiParam(value = "ID of the cart", required = true) @PathVariable("cartId") UUID cartId) {
                
        CartResponse cart = cartService.clearCart(cartId);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/items/{cartItemId}")
    @ApiOperation(value = "Delete Cart Item", notes = "Delete a specific item from the cart")
    public ResponseEntity<?> deleteCartItem(
            @ApiParam(value = "ID of the cart item", required = true) @PathVariable("cartItemId") UUID cartItemId) {
        CartResponse cart = cartService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping()
    @ApiOperation(value = "Delete Expired Carts", notes = "Delete expired shopping carts")
    public ResponseEntity<?> deleteExpiredCarts() {
        int deletedCarts = cartService.deleteExpiredCarts();
        SuccessResponse successResponse = SuccessResponse.builder()
                .type("Success")
                .message("Deleted expired carts: " + deletedCarts)
                .build();
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

}
