package com.company.demo.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class AsyncService {
    private static final Logger log = LoggerFactory.getLogger(AsyncService.class);

    @Async
    public CompletableFuture<String> getResult(String parameter) throws InterruptedException {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int threadSleepTime = random.nextInt(1000, 3000);
        log.info("Thread: {} sleep time: {}", Thread.currentThread().getName(), threadSleepTime);
        Thread.sleep(threadSleepTime);
        return CompletableFuture.completedFuture(parameter.toUpperCase());
    }
}