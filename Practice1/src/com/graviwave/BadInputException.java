package com.graviwave;

public class BadInputException extends RuntimeException {
    public BadInputException(String message) {
        super(message);
    }
}