package com.project.springecommerceapi.exceptions;

public class InvalidImageFileException extends RuntimeException {
    public InvalidImageFileException() {
    }

    public InvalidImageFileException(String message) {
        super(message);
    }
}
