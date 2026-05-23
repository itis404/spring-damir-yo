package com.itis.test4.exception;

public class OptionNotFoundException extends RuntimeException {
    public OptionNotFoundException(String message) {
        super(message);
    }
}