package com.project.springecommerceapi.exceptions;

public class ColorProductVariantNotFoundException extends RuntimeException {
    public ColorProductVariantNotFoundException() {
    }

    public ColorProductVariantNotFoundException(String message) {
        super(message);
    }
}