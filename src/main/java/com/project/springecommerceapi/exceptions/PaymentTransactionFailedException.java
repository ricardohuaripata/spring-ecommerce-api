package com.project.springecommerceapi.exceptions;

public class PaymentTransactionFailedException extends RuntimeException {
    public PaymentTransactionFailedException() {
    }

    public PaymentTransactionFailedException(String message) {
        super(message);
    }
}
