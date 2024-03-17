package com.myapp.TODO.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidation {

    @Value("${password.min.length}")
    private int MIN_LENGTH;

    @Value("${password.max.length}")
    private int MAX_LENGTH;

    private boolean validateNotNullOrEmpty(String password) {
        return password != null && !password.isEmpty();
    }

    private boolean validateMinLength(String password) {
        return password.length() >= MIN_LENGTH;
    }

    private boolean validateMaxLength(String password) {
        return password.length() <= MAX_LENGTH;
    }

    private boolean validateContainsLowercase(String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean validateContainsUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean validateContainsSymbol(String password) {
        return password.matches(".*[!@#$%^&*()\\-_=+|\\[{\\]};:'\",<.>/?].*");
    }

    public boolean validatePassword(String password) {
        return validateNotNullOrEmpty(password) &&
               validateMinLength(password) &&
               validateMaxLength(password) &&
               validateContainsLowercase(password) &&
               validateContainsUppercase(password) &&
               validateContainsSymbol(password);
    }
}