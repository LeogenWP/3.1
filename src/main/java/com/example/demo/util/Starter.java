package com.example.demo.util;

import com.example.demo.dao.CompanyRepository;
import com.example.demo.model.Company;
import com.example.demo.model.Symbol;
import com.example.demo.service.Executor;
import com.example.demo.service.http.AllSymbolsGetter;
import com.example.demo.util.cache.CompanyManager;
import com.example.demo.util.cache.QueueManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Starter {

    @Autowired
    private CompanyRepository repo;
    @Autowired
    private Executor executor;

    public void restoreCompanyCache() {
        List<Company> list = repo.getAllCompanies();
        for (Company company : list) {
            CompanyManager.getCompanies().put(company.getSymbol(), company);
        }
    }

    @Scheduled
    public void refreshStockData() {
        if (QueueManager.getQueue().size() == 0) {
            Symbol[] symbols = AllSymbolsGetter.getAllSymbols();
            for (Symbol symbol : symbols) {
                QueueManager.getQueue().add(symbol);
            }
            executor.startWork();
        }
    }
}
