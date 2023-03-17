package com.example.demo.service.http;

import com.example.demo.model.Symbol;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AllSymbolsGetter {
    private static final String TOKEN = "pk_b6b727dabd074898aed1d7d78b20c9e3";
    private static final String URL = "https://cloud.iexapis.com/stable/ref-data/iex/symbols?token=";

    public static Symbol[] getAllSymbols() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Symbol[]> response =
                restTemplate.getForEntity(
                        URL + TOKEN,
                        Symbol[].class);
        Symbol[] employees = response.getBody();
        return employees;
    }
}
