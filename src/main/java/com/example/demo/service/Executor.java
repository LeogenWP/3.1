package com.example.demo.service;

import com.example.demo.dao.CompanyRepository;
import com.example.demo.model.Company;
import com.example.demo.util.cache.CompanyManager;
import com.example.demo.util.cache.QueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class Executor {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Autowired
    private CompanyRepository repo;

    public void startWork()  {

        while (QueueManager.getQueue().size() > 0) {
            for (int i = 0; i < 6; i++) {
                try {
                    CompanyCallable company = new CompanyCallable();
                    Future<Company> companyFuture = executor.submit(company);
                    if (companyFuture.get() != null) {
                        saveCompany(companyFuture.get());
                    }
                } catch (InterruptedException e) {
                    System.out.println("!!!!! " + e);
                } catch (ExecutionException e) {
                    System.out.println("!!!!! " + e);
                }
            }
        }

    }

    private void saveCompany(Company company) {
        Company cashedCompany = CompanyManager.getCompanies().get(company.getSymbol());
        if (cashedCompany == null || !cashedCompany.equals(company)) {
            repo.save(company);
            CompanyManager.getCompanies().put(company.getSymbol(), company);
        }
    }

}
