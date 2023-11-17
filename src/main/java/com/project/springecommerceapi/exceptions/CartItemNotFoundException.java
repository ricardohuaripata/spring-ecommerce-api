package com.project.springecommerceapi.exceptions;

public class CartItemNotFoundException extends RuntimeException {
    public CartItemNotFoundException() {
    }

    public CartItemNotFoundException(String message) {
        super(message);
    }
}