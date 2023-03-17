package com.example.demo.util.cache;

import com.example.demo.model.Company;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CompanyManager {
    private static final Map<String,Company> companies = new ConcurrentHashMap();

    public static Map<String,Company> getCompanies() {
        return companies;
    }
}
