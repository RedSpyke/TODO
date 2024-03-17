package com.myapp.TODO.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class MinMaxAgeValidator implements ConstraintValidator<MinMaxAge, Date> {
    @Override
    public boolean isValid(Date birthday, ConstraintValidatorContext context) {
        if (birthday == null) {
            return true;
        }

        LocalDate localDateBirthday = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int age = Period.between(localDateBirthday, LocalDate.now()).getYears();
        return age >= 18 && age <= 100;
    }
}