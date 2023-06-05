package com.example.demo.service;

import com.example.demo.client.IexApiClient;
import com.example.demo.model.CompanyEntity;
import com.example.demo.model.SymbolEntity;
import com.example.demo.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final IexApiClient iexApiClient;
    private final CompanyRepository companyRepository;

    public void processCompanyDetails() {

        SymbolEntity[] array = iexApiClient.getAllSymbols();

        List<CompletableFuture<CompanyEntity>> completableFutures = Arrays.stream(array).toList().parallelStream().map(iexApiClient::getCompanyData).toList();

        List<CompanyEntity> companyEntities = completableFutures
                .parallelStream()
                .map(CompletableFuture::join)
                .toList();

        List<CompanyEntity> listWithoutNulls = companyEntities.parallelStream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        companyRepository.saveAll(listWithoutNulls);
    }
}
