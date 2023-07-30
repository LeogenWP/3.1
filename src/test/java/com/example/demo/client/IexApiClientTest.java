package com.example.demo.client;

import com.example.demo.model.CompanyEntity;
import com.example.demo.model.SymbolEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.concurrent.CompletableFuture;

class IexApiClientTest {
    private IexApiClient client = Mockito.mock(IexApiClient.class);

    @Test
    void getCompanyData() {
        SymbolEntity symbolEntity = SymbolEntity.builder()
                .symbol("DOYU")
                .build();
        CompanyEntity companyEntity = CompanyEntity.builder()
                .symbol("DOYU")
                .build();
        Mockito.when(client.getCompanyData(symbolEntity)).thenReturn(CompletableFuture.completedFuture(companyEntity));
        CompletableFuture<CompanyEntity> completableFuture = client.getCompanyData(symbolEntity);
        Assertions.assertEquals(symbolEntity.getSymbol(), completableFuture.join().getSymbol());
    }
}