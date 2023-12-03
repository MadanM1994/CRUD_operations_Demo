package com.madan.crud_operations_demo.validation.annotations;

import com.madan.crud_operations_demo.validation.validator.EmailValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface EmailValidation {
    String message() default "Enter a valid E-mail Address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
