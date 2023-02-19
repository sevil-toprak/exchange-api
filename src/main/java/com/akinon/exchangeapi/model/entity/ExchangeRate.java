package com.akinon.exchangeapi.model.entity;

import com.akinon.exchangeapi.model.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "exchange_rate")
public class ExchangeRate extends BaseEntity {

    @Column(name = "transaction_id")
    private String transactionId; // Random UUID

    @Enumerated(EnumType.STRING)
    @Column(name = "source_currency")
    private Currency sourceCurrency;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "target_currencies")
    private List<Currency> targetCurrencies;

    @Column(name = "source_amount")
    private BigDecimal sourceAmount;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "target_amounts")
    private List<BigDecimal> targetAmounts;
}