package com.example.demo.util;

import com.example.demo.dao.CompanyRepository;
import com.example.demo.model.Symbol;
import com.example.demo.service.DB.CompanyInfoUpdater;
import com.example.demo.service.http.AllSymbolsGetter;
import com.example.demo.util.cache.QueueManager;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Starter {

    private final CompanyRepository repo;
    private final AllSymbolsGetter allSymbolsGetter;
    private final CompanyInfoUpdater companyInfoUpdater;
    @Scheduled(fixedRate = 300 * 1000)
    public void refreshStockData() {
        if (QueueManager.getQueue().size() == 0) {
            Symbol[] symbols = allSymbolsGetter.getAllSymbols();
            for (Symbol symbol : symbols) {
                QueueManager.getQueue().add(symbol);
            }
            companyInfoUpdater.startWork();
        }
    }
}
