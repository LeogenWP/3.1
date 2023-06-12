package com.example.demo.client;

import com.example.demo.AppTests;
import com.example.demo.model.CompanyEntity;
import com.example.demo.model.SymbolEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class IexApiClientTest extends AppTests {

    @Autowired
    private IexApiClient client;

    @Test
    void getCompanyData() {
        SymbolEntity symbolEntity = SymbolEntity.builder()
                .symbol("DOYU")
                .build();
        CompletableFuture<CompanyEntity> completableFuture = client.getCompanyData(symbolEntity);
        Assertions.assertEquals(symbolEntity.getSymbol(), completableFuture.join().getSymbol());
    }

    @Test
    void getAllSymbols() {
        SymbolEntity[] employees =  client.getAllSymbols();
        Assertions.assertTrue(employees.length > 0);
    }

}