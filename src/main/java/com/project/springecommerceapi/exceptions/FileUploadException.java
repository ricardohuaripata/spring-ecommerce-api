package com.project.springecommerceapi.exceptions;

public class FileUploadException extends RuntimeException {
    public FileUploadException() {
    }

    public FileUploadException(String message) {
        super(message);
    }
}
