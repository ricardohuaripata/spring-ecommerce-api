package com.project.springecommerceapi.exceptions;

public class InvalidOldPasswordException extends RuntimeException {
    public InvalidOldPasswordException() {
    }

    public InvalidOldPasswordException(String message) {
        super(message);
    }
}