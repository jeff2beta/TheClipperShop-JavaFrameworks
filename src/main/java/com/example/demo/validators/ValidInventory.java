package com.example.demo.validators;

import org.hibernate.validator.internal.constraintvalidators.bv.number.bound.MaxValidatorForByte;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {MaxValidator.class})
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface ValidInventory {
    String message() default "No more than 200 parts.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
