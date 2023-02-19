package com.akinon.exchangeapi.model.response;

import com.akinon.exchangeapi.model.enums.Currency;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class FilterExchangeRateOrTransactionResponse {
    private List<Rate> exchangeRateList;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class Rate {
        private String transactionId;
        private Currency sourceCurrency;
        private List<Currency> targetCurrencies;
        private BigDecimal sourceAmount;
        private List<BigDecimal> targetAmounts;
        private Date createdAt;
    }
}