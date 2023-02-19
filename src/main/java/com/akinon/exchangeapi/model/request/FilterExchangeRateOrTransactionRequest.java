package com.akinon.exchangeapi.model.request;

import com.akinon.exchangeapi.validation.annotation.ShouldBeSelectedOnlyOneField;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Data
@ShouldBeSelectedOnlyOneField(fields = {"transactionId", "startDate", "endDate"})
public class FilterExchangeRateOrTransactionRequest {
    private String transactionId;
    private Date startDate;
    private Date endDate;
}