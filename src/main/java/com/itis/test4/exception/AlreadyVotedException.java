package com.itis.test4.exception;

public class AlreadyVotedException extends RuntimeException {
    public AlreadyVotedException(String message) {
        super(message);
    }
}