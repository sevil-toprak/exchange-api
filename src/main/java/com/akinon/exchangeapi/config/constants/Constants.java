package com.akinon.exchangeapi.config.constants;

public final class Constants {

    public static final String SPLIT_COMMA = ",";

    public static class ErrorMessage {
        public static final String CURRENCY_DOES_NOT_EXIST_EXCEPTION = "Currency %s does not exist.";
        public static final String GET_EXCHANGE_RATE_EXCEPTION = "Error occurred getting exchange rate exception.";
        public static final String GET_EXCHANGE_RATE_AMOUNT_EXCEPTION = "Error occurred getting exchange rate amount exception.";
        public static final String FILTER_EXCHANGE_RATE_OR_TRANSACTION_EXCEPTION = "Error occurred filtering exchange rate.";
        public static final String SHOULD_BE_SELECTED_ONLY_ONE_FIELD = "Should be send only transactionId or date fields";
    }

    public static class ErrorCodes {
        public static final int CURRENCY_DOES_NOT_EXIST_EXCEPTION = 1001;
        public static final int GET_EXCHANGE_RATE_EXCEPTION = 1002;
        public static final int GET_EXCHANGE_RATE_AMOUNT_EXCEPTION = 1003;
        public static final int FILTER_EXCHANGE_RATE_OR_TRANSACTION_EXCEPTION = 1004;
        public static final int SHOULD_BE_SELECTED_ONLY_ONE_FIELD =1005;
    }

    public static class CurrencyApi {
        public static final String API_KEY_TEXT = "apikey";
        public static final String SYMBOLS = "symbols";
        public static final String BASE = "base";
    }
}
