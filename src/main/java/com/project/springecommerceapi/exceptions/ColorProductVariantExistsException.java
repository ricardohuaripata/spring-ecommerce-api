package com.project.springecommerceapi.exceptions;

public class ColorProductVariantExistsException extends RuntimeException {
    public ColorProductVariantExistsException() {
    }

    public ColorProductVariantExistsException(String message) {
        super(message);
    }
}
