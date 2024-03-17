package com.myapp.TODO.exception;


public class PasswordChangeFailedException extends RuntimeException {
    public PasswordChangeFailedException(String message) {
        super(message);
    }
}