package com.project.springecommerceapi.exceptions;

public class HexcodeExistsException extends RuntimeException {
    public HexcodeExistsException() {
    }

    public HexcodeExistsException(String message) {
        super(message);
    }
}
