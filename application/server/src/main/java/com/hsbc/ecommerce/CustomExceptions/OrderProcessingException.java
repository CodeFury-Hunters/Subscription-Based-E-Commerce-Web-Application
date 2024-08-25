package com.hsbc.ecommerce.CustomExceptions;

public class OrderProcessingException extends RuntimeException {
    public OrderProcessingException(String message) {
        super(message);
    }
}