package com.madan.crud_operations_demo.validation.validator;

import com.madan.crud_operations_demo.validation.annotations.ZipCodeValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCodeValidation, String> {
    @Override
    public void initialize(ZipCodeValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String zipcode, ConstraintValidatorContext constraintValidatorContext) {
        return zipcode != null && zipcode.matches("\\d{5,}");
    }
}
