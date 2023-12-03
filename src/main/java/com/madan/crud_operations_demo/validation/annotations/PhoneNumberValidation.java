package com.madan.crud_operations_demo.validation.annotations;

import com.madan.crud_operations_demo.validation.validator.PhoneNumberValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumberValidation {
    String message() default "Phone number must be exactly 10 digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
