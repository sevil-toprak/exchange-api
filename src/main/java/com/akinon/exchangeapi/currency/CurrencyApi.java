package com.akinon.exchangeapi.currency;

import com.akinon.exchangeapi.config.constants.Constants;
import com.akinon.exchangeapi.config.properties.Properties;
import com.akinon.exchangeapi.currency.model.GetLatestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CurrencyApi {
    private final RestTemplate restTemplate;
    private final Properties properties;

    // documentation url: https://apilayer.com/marketplace/fixer-api
    public Map<String, BigDecimal> getLatestApi(String base, String symbols) {
        HttpHeaders headers = buildGetLatestApiHttpHeaders();
        UriComponentsBuilder builder = buildGetLatestApiUriComponents(base, symbols);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<GetLatestApiResponse> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                GetLatestApiResponse.class
        );
        return Objects.requireNonNull(response.getBody()).getRates();
    }

    private UriComponentsBuilder buildGetLatestApiUriComponents(String base, String symbols) {
        String url = properties.getApiUrl() + "/latest";
        return UriComponentsBuilder.fromUriString(url)
                .queryParam(Constants.CurrencyApi.BASE, base)
                .queryParam(Constants.CurrencyApi.SYMBOLS, symbols);
    }

    private HttpHeaders buildGetLatestApiHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.CurrencyApi.API_KEY_TEXT,  properties.getApiKey());
        return headers;
    }
}