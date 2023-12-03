package com.madan.crud_operations_demo.validation.annotations;

import com.madan.crud_operations_demo.validation.validator.ZipCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ZipCodeValidator.class)
public @interface ZipCodeValidation {
    String message() default "ZIP CODE must be at least 5 digits";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
