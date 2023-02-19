package com.akinon.exchangeapi.validation;

import com.akinon.exchangeapi.config.constants.Constants;
import com.akinon.exchangeapi.model.request.FilterExchangeRateOrTransactionRequest;
import com.akinon.exchangeapi.validation.annotation.ShouldBeSelectedOnlyOneField;
import com.akinon.exchangeapi.validation.validator.ShouldBeSelectedOnlyOneFieldValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Annotation;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ShouldBeSelectedOnlyOneFieldTest {
    private ShouldBeSelectedOnlyOneFieldValidator shouldBeSelectedOnlyOneFieldValidator;
    private ConstraintValidatorContext constraintValidatorContext = mock(ConstraintValidatorContext.class);
    ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder = mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);

    String[] fields = {"transactionId", "startDate", "endDate"};

    @BeforeEach
    void setUp() {
        shouldBeSelectedOnlyOneFieldValidator = new ShouldBeSelectedOnlyOneFieldValidator();
        shouldBeSelectedOnlyOneFieldValidator.initialize(createAnnotation(fields));
    }

    @Test
    void shouldVerify_SelectedOnlyOneField_Valid() {
        FilterExchangeRateOrTransactionRequest request = new FilterExchangeRateOrTransactionRequest();
        request.setTransactionId("b621872a-c65d-467f-9607-f5c29aa62cc8");
        request.setStartDate(null);
        request.setEndDate(null);

        boolean result = shouldBeSelectedOnlyOneFieldValidator.isValid(request, constraintValidatorContext);
        assertTrue(result);
    }

    @Test
    void shouldVerify_SelectedOnlyOneField_InValid() {
        FilterExchangeRateOrTransactionRequest request = new FilterExchangeRateOrTransactionRequest();
        request.setTransactionId("b621872a-c65d-467f-9607-f5c29aa62cc8");
        request.setStartDate(new Date());
        request.setEndDate(new Date());

        boolean result = shouldBeSelectedOnlyOneFieldValidator.isValid(request, constraintValidatorContext);
        assertFalse(result);
    }

    private ShouldBeSelectedOnlyOneField createAnnotation(String[] fields) {
        return new ShouldBeSelectedOnlyOneField() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String message() {
                return Constants.ErrorMessage.SHOULD_BE_SELECTED_ONLY_ONE_FIELD;
            }

            @Override
            public Class<?>[] groups() {
                return new Class[0];
            }

            @Override
            public Class<? extends Payload>[] payload() {
                return new Class[0];
            }

            @Override
            public String[] fields() {
                return fields;
            }
        };
    }
}
