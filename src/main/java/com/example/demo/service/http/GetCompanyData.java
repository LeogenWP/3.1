package com.example.demo.service.http;

import com.example.demo.model.Company;
import com.example.demo.model.Symbol;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class GetCompanyData {
    private static final String TOKEN = "pk_3576a69c7743495aab464491a370ced7";
    private static final String URL = "https://cloud.iexapis.com/stable/stock/%s/quote?token=";

    public static Company getCompanyData(Symbol symbol) {
        String urlWithSymbol = String.format(URL, symbol.getSymbol());
        RestTemplate restTemplate = new RestTemplate();
        Company company = null;

        System.out.println("!!!!! " + symbol);
        System.out.println("urlWithSymbol: " + urlWithSymbol);
        try {
            ResponseEntity<Company> response =
                    restTemplate.getForEntity(
                            urlWithSymbol + TOKEN,
                            Company.class);
            company = response.getBody();
        } catch (Exception e) {
            System.out.println("Exception for symbol: " + symbol + ": " + e);
        }
        return company;
    }
}
