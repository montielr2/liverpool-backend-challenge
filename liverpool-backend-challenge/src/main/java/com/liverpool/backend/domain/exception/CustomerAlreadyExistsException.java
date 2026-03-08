package com.liverpool.backend.domain.exception;

public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String userId) {
        super("Customer already exists with userId: " + userId);
    }
}
