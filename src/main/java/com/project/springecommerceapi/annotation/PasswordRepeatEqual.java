package com.project.springecommerceapi.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.project.springecommerceapi.validator.PasswordRepeatEqualValidator;

@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordRepeatEqualValidator.class)
public @interface PasswordRepeatEqual {
    String message() default "Password mismatch";

    String passwordFieldFirst();

    String passwordFieldSecond();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
