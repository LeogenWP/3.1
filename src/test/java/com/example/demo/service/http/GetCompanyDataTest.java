package com.example.demo.service.http;

import com.example.demo.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class GetCompanyDataTest {

    @Test
    void getCompanyData() {
        String TOKEN = "pk_b6b727dabd074898aed1d7d78b20c9e3";
        String URL = "https://cloud.iexapis.com/stable/stock/%s/quote?token=";

        String symbol = "AACIW";

        String urlWithSymbol = String.format(URL, symbol);
        RestTemplate restTemplate = new RestTemplate();
        Company company = null;

        try {
            ResponseEntity<Company> response =
                    restTemplate.getForEntity(
                            urlWithSymbol + TOKEN,
                            Company.class);
            company = response.getBody();
        } catch (Exception e) {
            System.out.println("Exception for symbol: " + symbol + ": " + e);
        }
    }
}