package com.example.demo.service.DB;

import com.example.demo.dao.CompanyRepository;
import com.example.demo.model.Company;
import com.example.demo.util.Executor;
import com.example.demo.util.cache.QueueManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

@Component
@RequiredArgsConstructor
public class CompanyInfoUpdater {

    private final CompanyRepository repo;
    private final Executor executor;

    @Value("${executor.treads-amount}")
    private int treadNums;

    public void startWork()  {

        List<Future<Company>> compamiesFuture = Collections.synchronizedList(new ArrayList<>());

        while (QueueManager.getQueue().size() > 0) {
            for (int i = 0; i < treadNums; i++) {
                CompanyCallable company = new CompanyCallable();
                compamiesFuture.add(executor.getExecutor().submit(company));
            }
        }
        saveCompanies(compamiesFuture);
    }

    private void saveCompanies(List<Future<Company>> compamiesFuture) {
        List<Company> companies = new ArrayList<>();
        for (Future<Company> future : compamiesFuture) {
            try {
                if (future.get() != null) {
                    companies.add(future.get());
                }
            } catch (Exception e) {
                System.out.println("Smth bad happens in CompanyInfoUpdater.saveCompanies: " + e);
            }
        }
        repo.saveAll(companies);
    }

}
