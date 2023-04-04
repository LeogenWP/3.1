package com.example.demo.service.DB;

import com.example.demo.model.Company;
import com.example.demo.model.Symbol;
import com.example.demo.service.http.GetCompanyData;
import com.example.demo.util.cache.QueueManager;
import java.util.concurrent.Callable;

public class CompanyCallable implements Callable {

    @Override
    public Company call() throws Exception {
        Symbol symbol = QueueManager.getQueue().take();
        Company company = GetCompanyData.getCompanyData(symbol);
        return company;
    }
}
