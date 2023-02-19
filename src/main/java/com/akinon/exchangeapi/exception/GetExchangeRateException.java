package com.akinon.exchangeapi.exception;

import com.akinon.exchangeapi.config.constants.Constants;
import org.springframework.http.HttpStatus;

public class GetExchangeRateException extends BaseException {
    public GetExchangeRateException() {
        super(Constants.ErrorMessage.GET_EXCHANGE_RATE_EXCEPTION,
                Constants.ErrorCodes.GET_EXCHANGE_RATE_EXCEPTION,
                HttpStatus.BAD_REQUEST);
    }
}