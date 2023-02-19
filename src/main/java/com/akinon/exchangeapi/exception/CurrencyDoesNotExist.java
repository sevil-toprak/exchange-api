package com.akinon.exchangeapi.exception;


import com.akinon.exchangeapi.config.constants.Constants;
import org.springframework.http.HttpStatus;

public class CurrencyDoesNotExist extends BaseException {
    public CurrencyDoesNotExist(String currency) {
        super(String.format(Constants.ErrorMessage.CURRENCY_DOES_NOT_EXIST_EXCEPTION, currency),
                Constants.ErrorCodes.CURRENCY_DOES_NOT_EXIST_EXCEPTION,
                HttpStatus.BAD_REQUEST);
    }
}