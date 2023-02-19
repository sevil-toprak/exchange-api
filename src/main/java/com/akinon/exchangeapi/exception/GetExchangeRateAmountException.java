package com.akinon.exchangeapi.exception;

import com.akinon.exchangeapi.config.constants.Constants;
import org.springframework.http.HttpStatus;

public class GetExchangeRateAmountException extends BaseException {
    public GetExchangeRateAmountException() {
        super(Constants.ErrorMessage.GET_EXCHANGE_RATE_AMOUNT_EXCEPTION,
                Constants.ErrorCodes.GET_EXCHANGE_RATE_AMOUNT_EXCEPTION,
                HttpStatus.BAD_REQUEST);
    }
}