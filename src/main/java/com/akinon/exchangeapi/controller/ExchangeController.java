package com.akinon.exchangeapi.controller;


import com.akinon.exchangeapi.model.request.FilterExchangeRateOrTransactionRequest;
import com.akinon.exchangeapi.model.response.FilterExchangeRateOrTransactionResponse;
import com.akinon.exchangeapi.model.response.GetExchangeRateAmountResponse;
import com.akinon.exchangeapi.model.response.GetExchangeRateResponse;
import com.akinon.exchangeapi.service.ExchangeService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/exchange-service")
public class ExchangeController {
    private final ExchangeService exchangeService;

    @ApiOperation("Get exchange rate")
    @GetMapping("/exchange/rate")
    public ResponseEntity<GetExchangeRateResponse> getExchangeRate(@RequestParam(value = "base") String base,
                                                                   @RequestParam(value = "target") String target) {
        return ResponseEntity.ok(exchangeService.getExchangeRate(base, target));
    }

    @ApiOperation("Get exchange rate amount")
    @GetMapping("/exchange/amount")
    public ResponseEntity<GetExchangeRateAmountResponse> getExchangeRateAmount(@RequestParam(value = "amount") BigDecimal amount,
                                                                               @RequestParam(value = "base") String base,
                                                                               @RequestParam(value = "target") String target) {
        return ResponseEntity.ok(exchangeService.getExchangeRateAmount(amount, base, target));
    }

    @ApiOperation("Filter transaction or exchange rate")
    @PostMapping("/exchange/filter")
    public ResponseEntity<FilterExchangeRateOrTransactionResponse> filterExchangeRateOrTransaction(
            @RequestBody @Valid FilterExchangeRateOrTransactionRequest request) {
        return ResponseEntity.ok(exchangeService.filterExchangeRateOrTransaction(request));
    }
}