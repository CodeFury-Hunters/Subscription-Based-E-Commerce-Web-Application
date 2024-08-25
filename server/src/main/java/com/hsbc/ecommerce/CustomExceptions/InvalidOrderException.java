package com.hsbc.ecommerce.CustomExceptions;

public class InvalidOrderException  extends RuntimeException{

    public InvalidOrderException(String message) {
        super(message);
    }
}
