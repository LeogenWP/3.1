package com.example.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class Executor {
    @Value("${executor.treads-amount}")
    private int treadNums;

    ExecutorService executorService;

    public synchronized ExecutorService getExecutor() {
        if (this.executorService == null) {
            executorService = Executors.newFixedThreadPool(treadNums);
        }
        return executorService;
    }
}
