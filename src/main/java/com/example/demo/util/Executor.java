package com.example.demo.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class Executor {
    @Value("${executor.treads-amount}")
    private int treadNums;
    @Bean
    public ExecutorService getExecutor() {
        return Executors.newFixedThreadPool(treadNums);
    }
}
