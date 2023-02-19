package com.akinon.exchangeapi.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class GetExchangeRateResponse {
    private List<Rate> rateList;

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