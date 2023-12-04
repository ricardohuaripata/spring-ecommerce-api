package com.project.springecommerceapi.exceptions;

public class ProductImageNotFoundException extends RuntimeException {
    public ProductImageNotFoundException() {
    }

    public ProductImageNotFoundException(String message) {
        super(message);
    }
}