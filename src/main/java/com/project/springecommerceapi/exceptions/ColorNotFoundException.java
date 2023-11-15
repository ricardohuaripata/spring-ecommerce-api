package com.project.springecommerceapi.exceptions;

public class ColorNotFoundException extends RuntimeException {
    public ColorNotFoundException() {
    }

    public ColorNotFoundException(String message) {
        super(message);
    }
}