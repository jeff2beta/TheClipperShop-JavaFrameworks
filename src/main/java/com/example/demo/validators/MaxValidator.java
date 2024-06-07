package com.example.demo.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class MaxValidator implements ConstraintValidator<ValidInventory, Integer>{
    @Autowired
    private ApplicationContext context;

    public static ApplicationContext myContext;
    @Override
    public void initialize(ValidInventory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        if(context == null) return true;
        if(context != null) myContext = context;
        if(integer > 200) return false;
        else return true;
    }
}
