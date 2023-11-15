package com.project.springecommerceapi.exceptions;

public class SlugExistsException extends RuntimeException {
    public SlugExistsException() {
    }

    public SlugExistsException(String message) {
        super(message);
    }
}
