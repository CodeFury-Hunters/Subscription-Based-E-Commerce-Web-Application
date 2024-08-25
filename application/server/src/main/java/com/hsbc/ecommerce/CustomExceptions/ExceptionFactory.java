package com.hsbc.ecommerce.CustomExceptions;

public class ExceptionFactory {

    public static RuntimeException createException(String exceptionType, String message) {
        switch (exceptionType) {
            case "InvalidOrder":
                return new InvalidOrderException(message);
            case "OutOfStock":
                return new OutOfStockException(message);
            case "CustomerNotFound":
                return new CustomerNotFoundException(message);
            case "OrderProcessing":
                return new OrderProcessingException(message);
            case "PaymentFailed":
                return new PaymentFailedException(message);
            case "UnauthorizedAccess":
                return new UnauthorizedAccessException(message);
            case "DuplicateEntity":
                return new DuplicateEntityException(message);
            case "InvalidInput":
                return new InvalidInputException(message);
            case "ShippingException":
                return new ShippingException(message);
            default:
                throw new IllegalArgumentException("Unknown exception type: " + exceptionType);
        }
    }
}
