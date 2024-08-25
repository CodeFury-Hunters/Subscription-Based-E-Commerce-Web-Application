package com.hsbc.ecommerce.CustomExceptions;

public class InvalidInputException extends RuntimeException {
    public InvalidInputException(String message) {
        super(message);
    }
}