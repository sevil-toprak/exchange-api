package com.akinon.exchangeapi.config.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class Properties {
    @Value("${currency.api.apiKey}")
    private String apiKey;
    @Value("${currency.api.apiUrl}")
    private String apiUrl;
}