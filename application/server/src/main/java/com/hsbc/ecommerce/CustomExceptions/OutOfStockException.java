package com.hsbc.ecommerce.CustomExceptions;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String message) {
        super(message);
    }
}