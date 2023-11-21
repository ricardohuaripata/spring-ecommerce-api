package com.project.springecommerceapi.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException() {
    }

    public OrderNotFoundException(String message) {
        super(message);
    }
}