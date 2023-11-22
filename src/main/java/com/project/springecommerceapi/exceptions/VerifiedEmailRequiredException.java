package com.project.springecommerceapi.exceptions;

public class VerifiedEmailRequiredException extends RuntimeException {
    public VerifiedEmailRequiredException() {
    }

    public VerifiedEmailRequiredException(String message) {
        super(message);
    }
}
