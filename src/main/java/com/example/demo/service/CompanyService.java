package com.example.demo.service;

import com.example.demo.client.IexApiClient;
import com.example.demo.model.CompanyEntity;
import com.example.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final IexApiClient iexApiClient;
    private final CompanyRepository companyRepository;

    public void processCompanyDetails() {
        List<CompletableFuture<CompanyEntity>> completableFutures = Arrays.stream(iexApiClient.getAllSymbols())
                .map(iexApiClient::getCompanyData).toList();


        //TODO: handle 429 -> if yes - ignore
        List<CompanyEntity> companyEntities = completableFutures
                .parallelStream()
                .map(CompletableFuture::join)
                .toList();

        companyRepository.saveAll(companyEntities);
    }
}
