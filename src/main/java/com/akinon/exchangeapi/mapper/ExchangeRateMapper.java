package com.akinon.exchangeapi.mapper;

import com.akinon.exchangeapi.model.entity.ExchangeRate;
import com.akinon.exchangeapi.model.response.FilterExchangeRateOrTransactionResponse;
import com.akinon.exchangeapi.model.response.GetExchangeRateAmountResponse;
import com.akinon.exchangeapi.model.response.GetExchangeRateResponse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExchangeRateMapper {
    private ExchangeRateMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static GetExchangeRateResponse currencyApiResponseToGetExchangeRateResponse(Map<String, BigDecimal> currencyApiResponse) {
        GetExchangeRateResponse response = new GetExchangeRateResponse();
        List<GetExchangeRateResponse.Rate> rateList = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> mapEntry : currencyApiResponse.entrySet()) {
            GetExchangeRateResponse.Rate rate = new GetExchangeRateResponse.Rate();
            rate.setCurrency(mapEntry.getKey());
            rate.setValue(mapEntry.getValue());
            rateList.add(rate);
        }
        response.setRateList(rateList);
        return response;
    }

    public static GetExchangeRateAmountResponse currencyApiResponseToGetExchangeRateAmountResponse(Map<String,
            BigDecimal> currencyApiResponse, BigDecimal amount, String transactionId) {
        GetExchangeRateAmountResponse response = new GetExchangeRateAmountResponse();
        List<GetExchangeRateAmountResponse.Rate> rateList = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> mapEntry : currencyApiResponse.entrySet()) {
            GetExchangeRateAmountResponse.Rate rate = new GetExchangeRateAmountResponse.Rate();
            rate.setCurrency(mapEntry.getKey());
            rate.setValue(mapEntry.getValue().multiply(amount));
            rateList.add(rate);
        }
        response.setRateAmountList(rateList);
        response.setTransactionId(transactionId);
        return response;
    }

    public static FilterExchangeRateOrTransactionResponse exchangeRateListToFilterExchangeRate(List<ExchangeRate> exchangeRateList) {
        FilterExchangeRateOrTransactionResponse response = new FilterExchangeRateOrTransactionResponse();
        List<FilterExchangeRateOrTransactionResponse.Rate> rateList = new ArrayList<>();

        for (ExchangeRate exchangeRate : exchangeRateList) {
            FilterExchangeRateOrTransactionResponse.Rate rate = new FilterExchangeRateOrTransactionResponse.Rate();
            rate.setTransactionId(exchangeRate.getTransactionId());
            rate.setSourceCurrency(exchangeRate.getSourceCurrency());
            rate.setTargetCurrencies(exchangeRate.getTargetCurrencies());
            rate.setSourceAmount(exchangeRate.getSourceAmount());
            rate.setTargetAmounts(exchangeRate.getTargetAmounts());
            rate.setCreatedAt(exchangeRate.getCreatedAt());

            rateList.add(rate);
        }
        response.setExchangeRateList(rateList);
        return response;
    }
}