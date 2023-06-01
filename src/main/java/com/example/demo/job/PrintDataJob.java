package com.example.demo.job;

import com.example.demo.model.CompanyEntity;
import com.example.demo.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.List;

public class PrintDataJob {

    @Autowired
    private CompanyRepository repo;

    @Scheduled(fixedRate = 300 * 1000)
    public void buildTop5ByPrice() {
        List<CompanyEntity> list = repo.findTop5ByLatestPrice();
        List<CompanyEntity> sortedList = new ArrayList<>();
        if (!list.isEmpty()) {
            sortedList.add(list.get(0));
        }
        list.remove(0);
        list.stream().sorted((c1,c2) -> c1.getCompanyName().compareTo(c2.getCompanyName()));

        for (CompanyEntity companyEntity : list) {
            sortedList.add(companyEntity);
        }
        sortedList.forEach(System.out::println);
    }

    @Scheduled(fixedRate = 300 * 1000)
    public void buildTop5ByPercentChange() {
        List<CompanyEntity> list = repo.findTop5ByChangePercent();
        list.forEach(System.out::println);
    }
}
