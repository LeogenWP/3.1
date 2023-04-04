package com.example.demo.service.http;

import com.example.demo.model.Symbol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AllSymbolsGetter {
    @Value("${service.token}")
    private String TOKEN;
    @Value("${service.all-symbols-url}")
    private String URL;

    public Symbol[] getAllSymbols() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Symbol[]> response =
                restTemplate.getForEntity(
                        URL + TOKEN,
                        Symbol[].class);
        Symbol[] employees = response.getBody();
        return employees;
    }
}
