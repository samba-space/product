package com.anysinsa.product.application.exception;

public class NotExistProductException extends RuntimeException {
    public NotExistProductException(String message) {
        super(message);
    }
}
