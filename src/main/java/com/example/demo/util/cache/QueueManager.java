package com.example.demo.util.cache;

import com.example.demo.model.Symbol;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Component
public class QueueManager {
    private static final BlockingQueue<Symbol> blockingQueue = new LinkedBlockingDeque<>();

    public static BlockingQueue<Symbol> getQueue() {
        return blockingQueue;
    }
}
