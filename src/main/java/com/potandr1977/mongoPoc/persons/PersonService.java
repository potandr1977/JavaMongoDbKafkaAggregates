package com.potandr1977.mongoPoc.persons;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PersonService {
    @Async
    public void saveOrderDetails(Person person) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(person.getName());
    }

    @Async
    public CompletableFuture<String> saveOrderDetailsFuture(Person person) throws InterruptedException {
        System.out.println("Execute method with return type + " + Thread.currentThread().getName());
        String result = "Hello From CompletableFuture. Order: ".concat(person.getName());
        Thread.sleep(5000);
        return CompletableFuture.completedFuture(result);
    }

    @Async
    public CompletableFuture<String> compute(Person person) throws InterruptedException {
        String result = "Hello From CompletableFuture CHAIN. Order: ".concat(person.getName());
        Thread.sleep(5000);
        return CompletableFuture.completedFuture(result);
    }
}
