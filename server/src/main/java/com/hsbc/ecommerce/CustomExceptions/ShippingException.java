package com.hsbc.ecommerce.CustomExceptions;

public class ShippingException extends RuntimeException {
    public ShippingException(String message) {
        super(message);
    }
}