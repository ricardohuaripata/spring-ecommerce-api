package com.project.springecommerceapi.exceptions;

public class NoItemsToPayException extends RuntimeException {
    public NoItemsToPayException() {
    }

    public NoItemsToPayException(String message) {
        super(message);
    }
}
