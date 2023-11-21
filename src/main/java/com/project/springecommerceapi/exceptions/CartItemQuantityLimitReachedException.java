package com.project.springecommerceapi.exceptions;

public class CartItemQuantityLimitReachedException extends RuntimeException {
    public CartItemQuantityLimitReachedException() {
    }

    public CartItemQuantityLimitReachedException(String message) {
        super(message);
    }
}