package com.madan.crud_operations_demo.validation.validator;

import com.madan.crud_operations_demo.validation.annotations.NameValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameValidation, String> {

    @Override
    public void initialize(NameValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return name != null && name.length() > 2;
    }
}
