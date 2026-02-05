package com.chornarin.site.full_stack.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.hibernate.validator.internal.constraintvalidators.hv.UniqueElementsValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueElementsValidator.class)
public @interface EmailNotNull {

    String message() default "Email already exist";
    Class<?>[] groups() default{};
    Class <? extends Payload >[] payload() default {};

}
