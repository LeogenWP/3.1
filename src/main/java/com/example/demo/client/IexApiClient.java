package com.example.demo.client;

import com.example.demo.model.CompanyEntity;
import com.example.demo.model.SymbolEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class IexApiClient {
    @Value("${service.token}")
    private String TOKEN;
    @Value("${service.all-symbols-url}")
    private String URL;


    public CompletableFuture<CompanyEntity> getCompanyData(SymbolEntity symbolEntity) {
        String urlWithSymbol = String.format(URL, symbolEntity.getSymbol());
        RestTemplate restTemplate = new RestTemplate();
        CompanyEntity companyEntity = null;

        log.info(symbolEntity.toString());
        try {
            ResponseEntity<CompanyEntity> response =
                    restTemplate.getForEntity(
                            urlWithSymbol + TOKEN,
                            CompanyEntity.class);
            companyEntity = response.getBody();
        } catch (Exception e) {
            log.error("Exception for symbol: " + symbolEntity + ": " + e);
        }
        return CompletableFuture.completedFuture(companyEntity);
    }

    public SymbolEntity[] getAllSymbols() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SymbolEntity[]> response =
                restTemplate.getForEntity(
                        URL + TOKEN,
                        SymbolEntity[].class);
        SymbolEntity[] employees = response.getBody();
        return employees;
    }
}
