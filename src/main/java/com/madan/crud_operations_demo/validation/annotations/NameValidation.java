package com.madan.crud_operations_demo.validation.annotations;

import com.madan.crud_operations_demo.validation.validator.NameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = NameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValidation {
    String message() default "Name should be at least 2 charters long";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
