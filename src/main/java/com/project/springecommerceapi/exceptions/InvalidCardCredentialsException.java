package com.project.springecommerceapi.exceptions;

public class InvalidCardCredentialsException extends RuntimeException {
    public InvalidCardCredentialsException() {
    }

    public InvalidCardCredentialsException(String message) {
        super(message);
    }
}