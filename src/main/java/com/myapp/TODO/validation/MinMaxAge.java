package com.myapp.TODO.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinMaxAgeValidator.class)
public @interface MinMaxAge {

    String message() default "User must be at least 18 years old and not more than 100 years old";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
