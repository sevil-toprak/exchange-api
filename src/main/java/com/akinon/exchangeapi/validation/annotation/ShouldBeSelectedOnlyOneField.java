package com.akinon.exchangeapi.validation.annotation;

import com.akinon.exchangeapi.config.constants.Constants;
import com.akinon.exchangeapi.validation.validator.ShouldBeSelectedOnlyOneFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = ShouldBeSelectedOnlyOneFieldValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ShouldBeSelectedOnlyOneField {
    String message() default Constants.ErrorMessage.SHOULD_BE_SELECTED_ONLY_ONE_FIELD;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] fields();
}