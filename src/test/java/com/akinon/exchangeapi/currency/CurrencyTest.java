package com.akinon.exchangeapi.currency;

import com.akinon.exchangeapi.exception.CurrencyDoesNotExist;
import com.akinon.exchangeapi.model.enums.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CurrencyTest {
    @Test
    void shouldVerify_currency_Valid() {
        assertEquals(Currency.USD.toString(), "USD");
        assertEquals(Currency.EUR.toString(), "EUR");
        assertEquals(Currency.TRY.toString(), "TRY");
        assertEquals(Currency.GBP.toString(), "GBP");
        assertEquals(Currency.JPY.toString(), "JPY");

    }

    @Test
    void shouldVerify_currency_inValid() {
        Exception exception = assertThrows(CurrencyDoesNotExist.class, () -> Currency.fromValue("ABC"));
        assertEquals("Currency ABC does not exist.", exception.getMessage());
    }

}
