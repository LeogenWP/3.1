package com.example.demo.service.DB;

import com.example.demo.dao.CompanyRepository;
import com.example.demo.model.Company;
import com.example.demo.util.Executor;
import com.example.demo.util.cache.CompanyManager;
import com.example.demo.util.cache.QueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Component
public class CompanyInfoUpdater {

    @Autowired
    private CompanyRepository repo;

    @Autowired
    private Executor executor;

    public void startWork()  {

        while (QueueManager.getQueue().size() > 0) {
            for (int i = 0; i < 6; i++) {
                try {
                    CompanyCallable company = new CompanyCallable();
                    Future<Company> companyFuture = executor.getExecutor().submit(company);
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
