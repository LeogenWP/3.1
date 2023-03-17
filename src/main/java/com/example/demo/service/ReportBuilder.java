package com.example.demo.service;

import com.example.demo.dao.CompanyRepository;
import com.example.demo.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReportBuilder {

    @Autowired
    private CompanyRepository repo;

    @Scheduled(fixedRate = 300 * 1000)
    public void buildTop5ByPrice() {
        List<Company> list = repo.findTop5ByLatestPrice();
        List<Company> sortedList = new ArrayList<>();
        if (!list.isEmpty()) {
            sortedList.add(list.get(0));
        }
        list.remove(0);
        list.stream().sorted((c1,c2) -> c1.getCompanyName().compareTo(c2.getCompanyName()));

        for (Company company : list) {
            sortedList.add(company);
        }
        sortedList.forEach(System.out::println);
    }

    @Scheduled(fixedRate = 300 * 1000)
    public void buildTop5ByPercentChange() {
        List<Company> list = repo.findTop5ByChangePercent();
        list.forEach(System.out::println);
    }
}
