package com.madan.crud_operations_demo.validation.validator;

import com.madan.crud_operations_demo.validation.annotations.PhoneNumberValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumberValidation, String> {
    @Override
    public void initialize(PhoneNumberValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext constraintValidatorContext) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }
}
