package com.akinon.exchangeapi.service;

import com.akinon.exchangeapi.config.constants.Constants;
import com.akinon.exchangeapi.currency.CurrencyApi;
import com.akinon.exchangeapi.exception.FilterExchangeRateOrTransaction;
import com.akinon.exchangeapi.exception.GetExchangeRateAmountException;
import com.akinon.exchangeapi.exception.GetExchangeRateException;
import com.akinon.exchangeapi.mapper.ExchangeRateMapper;
import com.akinon.exchangeapi.model.entity.ExchangeRate;
import com.akinon.exchangeapi.model.enums.Currency;
import com.akinon.exchangeapi.model.request.FilterExchangeRateOrTransactionRequest;
import com.akinon.exchangeapi.model.response.FilterExchangeRateOrTransactionResponse;
import com.akinon.exchangeapi.model.response.GetExchangeRateAmountResponse;
import com.akinon.exchangeapi.model.response.GetExchangeRateResponse;
import com.akinon.exchangeapi.repository.CustomExchangeRepository;
import com.akinon.exchangeapi.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final CustomExchangeRepository customExchangeRepository;
    private final CurrencyApi currencyApi;

    public GetExchangeRateResponse getExchangeRate(String base, String target) {
        try {
            Map<String, BigDecimal> currencyApiResponse = currencyApi.getLatestApi(base, target);
            //saveExternalApi(base, BigDecimal.ONE, target, currencyApiResponse);
            return ExchangeRateMapper.currencyApiResponseToGetExchangeRateResponse(currencyApiResponse);
        } catch (Exception e) {
            log.error("An exception occurred getting exchange rate.", e);
            throw new GetExchangeRateException();
        }
    }

    public GetExchangeRateAmountResponse getExchangeRateAmount(BigDecimal amount, String base, String target) {
        try {
            Map<String, BigDecimal> currencyApiResponse = currencyApi.getLatestApi(base, target);
            ExchangeRate exchangeRate = saveExternalApi(base, amount, target, currencyApiResponse);
            return ExchangeRateMapper.currencyApiResponseToGetExchangeRateAmountResponse(currencyApiResponse, amount,
                    exchangeRate.getTransactionId());
        } catch (Exception e) {
            log.error("An exception occurred getting exchange rate amount.", e);
            throw new GetExchangeRateAmountException();
        }
    }

    public FilterExchangeRateOrTransactionResponse filterExchangeRateOrTransaction(FilterExchangeRateOrTransactionRequest request) {
        try {
            List<ExchangeRate> exchangeRateList = customExchangeRepository.filterExchangeRate(request);
            return ExchangeRateMapper.exchangeRateListToFilterExchangeRate(exchangeRateList);
        } catch (Exception e) {
            log.error("An exception occurred filtering exchange rate.", e);
            throw new FilterExchangeRateOrTransaction();
        }
    }

    private ExchangeRate saveExternalApi(String base, BigDecimal sourceAmount, String target, Map<String,
            BigDecimal> currencyApiResponse) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setCreatedAt(new Date());
        exchangeRate.setTransactionId(UUID.randomUUID().toString());
        exchangeRate.setSourceCurrency(Currency.fromValue(base));
        exchangeRate.setSourceAmount(sourceAmount);

        List<Currency> targetList = new ArrayList<>();
        List<BigDecimal> amountList = new ArrayList<>();
        for (String currency : target.split(Constants.SPLIT_COMMA)) {
            targetList.add(Currency.fromValue(currency));
            amountList.add(currencyApiResponse.get(currency).multiply(sourceAmount));
        }
        exchangeRate.setTargetCurrencies(targetList);
        exchangeRate.setTargetAmounts(amountList);

        return exchangeRepository.save(exchangeRate);
    }
}