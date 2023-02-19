package com.akinon.exchangeapi.validation.validator;

import com.akinon.exchangeapi.validation.annotation.ShouldBeSelectedOnlyOneField;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;


public class ShouldBeSelectedOnlyOneFieldValidator implements ConstraintValidator<ShouldBeSelectedOnlyOneField, Object> {

    private String[] fields;

    @Override
    public void initialize(ShouldBeSelectedOnlyOneField constraintAnnotation) {
        fields = constraintAnnotation.fields();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (fields.length != 3) {
            return false;
        }

        Object transactionId = new BeanWrapperImpl(value).getPropertyValue(fields[0]);
        Object startDate = new BeanWrapperImpl(value).getPropertyValue(fields[1]);
        Object endDate = new BeanWrapperImpl(value).getPropertyValue(fields[2]);

        return (!Objects.nonNull(transactionId) || !Objects.nonNull(startDate))
                && (!Objects.nonNull(transactionId) || !Objects.nonNull(endDate));
    }
}