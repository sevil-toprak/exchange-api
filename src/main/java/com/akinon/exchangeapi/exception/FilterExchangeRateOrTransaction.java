package com.akinon.exchangeapi.exception;

import com.akinon.exchangeapi.config.constants.Constants;
import org.springframework.http.HttpStatus;

public class FilterExchangeRateOrTransaction extends BaseException {
    public FilterExchangeRateOrTransaction() {
        super(Constants.ErrorMessage.FILTER_EXCHANGE_RATE_OR_TRANSACTION_EXCEPTION,
                Constants.ErrorCodes.FILTER_EXCHANGE_RATE_OR_TRANSACTION_EXCEPTION,
                HttpStatus.BAD_REQUEST);
    }
}