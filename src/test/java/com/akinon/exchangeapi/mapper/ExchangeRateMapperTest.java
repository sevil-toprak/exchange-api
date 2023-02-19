package com.akinon.exchangeapi.mapper;

import com.akinon.exchangeapi.model.entity.ExchangeRate;
import com.akinon.exchangeapi.model.enums.Currency;
import com.akinon.exchangeapi.model.response.FilterExchangeRateOrTransactionResponse;
import com.akinon.exchangeapi.model.response.GetExchangeRateAmountResponse;
import com.akinon.exchangeapi.model.response.GetExchangeRateResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ExchangeRateMapperTest {

    @Test
    void should_getExchangeRateResponse_when_currencyApiResponseToGetExchangeRateResponseMethod() {
        HashMap<String, BigDecimal> currencyApiResponse = new HashMap<>();
        BigDecimal eurRate = BigDecimal.valueOf(0.9328);
        BigDecimal tryRate = BigDecimal.valueOf(18.829695);
        currencyApiResponse.put("EUR", eurRate);
        currencyApiResponse.put("TRY", tryRate);

        GetExchangeRateResponse actualResponse = ExchangeRateMapper.currencyApiResponseToGetExchangeRateResponse(currencyApiResponse);

        GetExchangeRateResponse exceptedResponse = new GetExchangeRateResponse();
        List<GetExchangeRateResponse.Rate> rateList = new ArrayList<>();
        GetExchangeRateResponse.Rate rateEur = new GetExchangeRateResponse.Rate();
        rateEur.setCurrency("EUR");
        rateEur.setValue(BigDecimal.valueOf(0.9328));
        rateList.add(rateEur);
        GetExchangeRateResponse.Rate rateTry = new GetExchangeRateResponse.Rate();
        rateTry.setCurrency("TRY");
        rateTry.setValue(BigDecimal.valueOf(18.829695));
        rateList.add(rateTry);
        exceptedResponse.setRateList(rateList);

        assertThat(exceptedResponse).isEqualTo(actualResponse);
    }

    @Test
    void should_getExchangeRateAmountResponse_when_currencyApiResponseToGetExchangeRateAmountResponse() {
        HashMap<String, BigDecimal> currencyApiResponse = new HashMap<>();
        BigDecimal eurRate = BigDecimal.valueOf(0.9328);
        BigDecimal tryRate = BigDecimal.valueOf(18.829695);
        BigDecimal amount = BigDecimal.valueOf(100);
        currencyApiResponse.put("EUR", eurRate);
        currencyApiResponse.put("TRY", tryRate);
        String transactionId = "935413e6-e5e0-43f9-8034-78f6a28db228";

        GetExchangeRateAmountResponse actualResponse = ExchangeRateMapper
                .currencyApiResponseToGetExchangeRateAmountResponse(currencyApiResponse, amount, transactionId);

        GetExchangeRateAmountResponse exceptedResponse = new GetExchangeRateAmountResponse();
        List<GetExchangeRateAmountResponse.Rate> rateList = new ArrayList<>();
        GetExchangeRateAmountResponse.Rate rateEur = new GetExchangeRateAmountResponse.Rate();
        rateEur.setCurrency("EUR");
        rateEur.setValue(eurRate.multiply(amount));
        rateList.add(rateEur);
        GetExchangeRateAmountResponse.Rate rateTry = new GetExchangeRateAmountResponse.Rate();
        rateTry.setCurrency("TRY");
        rateTry.setValue(tryRate.multiply(amount));
        rateList.add(rateTry);
        exceptedResponse.setRateAmountList(rateList);
        exceptedResponse.setTransactionId(transactionId);

        assertThat(exceptedResponse).isEqualTo(actualResponse);
    }

    @Test
    void should_filterExchangeRate_when_exchangeRateListToFilterExchangeRate() {
        Date currentDate = new Date();
        BigDecimal ten = new BigDecimal(10);

        List<ExchangeRate> exchangeRateList = new ArrayList<>();
        ExchangeRate rate = new ExchangeRate();
        rate.setCreatedAt(currentDate);
        rate.setTransactionId("68f048e7-67dd-431f-913b-a0ca8dca8ffd");
        rate.setSourceCurrency(Currency.USD);
        rate.setTargetCurrencies(Arrays.asList(Currency.EUR, Currency.TRY));
        rate.setSourceAmount(ten);
        rate.setTargetAmounts(Arrays.asList(new BigDecimal("93.2800"), new BigDecimal("1881.55500")));
        exchangeRateList.add(rate);

        FilterExchangeRateOrTransactionResponse actualResponse = ExchangeRateMapper
                .exchangeRateListToFilterExchangeRate(exchangeRateList);

        FilterExchangeRateOrTransactionResponse exceptedResponse = new FilterExchangeRateOrTransactionResponse();
        List<FilterExchangeRateOrTransactionResponse.Rate> rateList = new ArrayList<>();

        for (ExchangeRate exchangeRate : exchangeRateList) {
            FilterExchangeRateOrTransactionResponse.Rate rateResponse = new FilterExchangeRateOrTransactionResponse.Rate();
            rateResponse.setTransactionId(exchangeRate.getTransactionId());
            rateResponse.setSourceCurrency(exchangeRate.getSourceCurrency());
            rateResponse.setTargetCurrencies(exchangeRate.getTargetCurrencies());
            rateResponse.setSourceAmount(exchangeRate.getSourceAmount());
            rateResponse.setTargetAmounts(exchangeRate.getTargetAmounts());
            rateResponse.setCreatedAt(exchangeRate.getCreatedAt());
            rateList.add(rateResponse);
        }
        exceptedResponse.setExchangeRateList(rateList);
        assertThat(exceptedResponse).isEqualTo(actualResponse);
    }
}
