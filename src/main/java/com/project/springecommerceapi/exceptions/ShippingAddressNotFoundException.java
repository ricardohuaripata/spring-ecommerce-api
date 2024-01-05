package com.project.springecommerceapi.exceptions;

public class ShippingAddressNotFoundException extends RuntimeException {
    public ShippingAddressNotFoundException() {
    }

    public ShippingAddressNotFoundException(String message) {
        super(message);
    }
}