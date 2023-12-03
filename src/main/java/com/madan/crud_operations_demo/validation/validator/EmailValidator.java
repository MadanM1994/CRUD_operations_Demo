package com.madan.crud_operations_demo.validation.validator;

import com.madan.crud_operations_demo.validation.annotations.EmailValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

    @Override
    public void initialize(EmailValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.matches("^(.+)@(\\S+)$");
    }
}
