package com.akinon.exchangeapi.model.enums;

import com.akinon.exchangeapi.exception.CurrencyDoesNotExist;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum Currency {
    TRY("TRY"),
    EUR("EUR"),
    USD("USD"),
    GBP("GBP"),
    JPY("JPY");

    private final String currency;

    public static Currency fromValue(String currency) {
        for (Currency c : values()) {
            if (Objects.equals(c.getCurrency(), currency))
                return c;
        }
        throw new CurrencyDoesNotExist(currency);
    }
}