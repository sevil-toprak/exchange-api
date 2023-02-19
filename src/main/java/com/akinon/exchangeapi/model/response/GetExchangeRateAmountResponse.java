package com.akinon.exchangeapi.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class GetExchangeRateAmountResponse {
    private List<Rate> rateAmountList;
    private String transactionId;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class Rate {
        private String currency;
        private BigDecimal value;
    }
}