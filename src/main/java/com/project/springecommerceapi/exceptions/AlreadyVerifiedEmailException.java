package com.project.springecommerceapi.exceptions;

public class AlreadyVerifiedEmailException extends RuntimeException {
    public AlreadyVerifiedEmailException() {
    }

    public AlreadyVerifiedEmailException(String message) {
        super(message);
    }
}