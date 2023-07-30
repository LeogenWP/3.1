package com.example.demo.service;

import com.example.demo.AppTests;
import com.example.demo.client.IexApiClient;
import com.example.demo.model.CompanyEntity;
import com.example.demo.model.SymbolEntity;
import com.example.demo.repository.CompanyRepository;
import org.junit.Assume;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
class CompanyServiceTest {
    CompanyRepository companyRepository = Mockito.mock(CompanyRepository.class);
    IexApiClient iexApiClient = Mockito.mock(IexApiClient.class);
    CompanyService companyService = new CompanyService(iexApiClient, companyRepository);

    @Test
    void processCompanyDetails() {
        Mockito.when(iexApiClient.getAllSymbols()).thenReturn(new SymbolEntity[] {
                new SymbolEntity("DOYU",null,true,"name"),
                new SymbolEntity("ACM",null,true,"name")});

        Mockito.when(iexApiClient.getCompanyData(Mockito.any()))
                .thenReturn(CompletableFuture.completedFuture(
                        new CompanyEntity("ACM","AECOM",0.01162f,86.2f)));
        doReturn(new SymbolEntity[] {
                new SymbolEntity("DOYU",null,true,"name"),
                new SymbolEntity("ACM",null,true,"name")})
                .when(iexApiClient).getAllSymbols();

        List<CompanyEntity> listWithoutNulls = companyService.processCompanyDetails();
        Assume.assumeNotNull(listWithoutNulls);
        assertFalse(listWithoutNulls.isEmpty());
        Mockito.verify(companyRepository).saveAll(listWithoutNulls);
    }

}
