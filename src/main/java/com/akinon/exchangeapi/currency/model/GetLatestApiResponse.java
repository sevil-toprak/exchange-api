package com.akinon.exchangeapi.currency.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class GetLatestApiResponse {
    private String base;
    private Date date;
    private boolean success;
    private long timestamp;
    private HashMap<String, BigDecimal> rates;
}