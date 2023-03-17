package com.example.demo.service;

import com.example.demo.model.Company;
import com.example.demo.model.Symbol;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

class AllSymbolsGetterTest {

    @Test
    public void getAllSymbols() throws Exception {
        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();
        blockingQueue.add("Hello1");
        blockingQueue.add("Hello2");
        System.out.println(blockingQueue.size());
        String h1 = blockingQueue.take();
        System.out.println(h1);
        System.out.println(blockingQueue.size());
    }

    @Test
    public void testMap() throws Exception {
        Map<String, Company> companies = new ConcurrentHashMap();
        Company company = new Company( "symbol", "companyName", 1.15f, 1.15f);
        Company company2 = new Company( "symbol", "companyName", 1.14f, 1.14f);

        companies.put("symbol", company);
        System.out.println(companies.size());
        companies.put("symbol", company2);
        System.out.println(companies.size());

        System.out.println(companies.get("symbol"));

    }

    @Test
    public void getAll() {
        String TOKEN = "pk_b6b727dabd074898aed1d7d78b20c9e3";
        String URL = "https://cloud.iexapis.com/stable/ref-data/iex/symbols?token=";

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity request  = RequestEntity
                .get(URI.create(URL + TOKEN))
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE).build();

        ResponseEntity<Symbol[]> response =
                restTemplate.getForEntity(
                        URL + TOKEN,
                        Symbol[].class);
        Symbol[] employees = response.getBody();

        System.out.println(employees);
        System.out.println(employees.length);
    }

    @Test
    public void save() {



    }

}