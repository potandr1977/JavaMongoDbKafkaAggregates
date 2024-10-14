package com.potandr1977.mongoPoc.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/process")
    public ResponseEntity<Void> process(@RequestBody Person person) throws InterruptedException {
        System.out.println("PROCESSING STARTED");
        personService.saveOrderDetails(person);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/process/future")
    public ResponseEntity<String> processFuture(@RequestBody Person person) throws InterruptedException, ExecutionException {
        System.out.println("PROCESSING STARTED");
        CompletableFuture<String> orderDetailsFuture = personService.saveOrderDetailsFuture(person);
        return ResponseEntity.ok(orderDetailsFuture.get());
    }

    @PostMapping("/process/future/chain")
    public ResponseEntity<Void> processFutureChain(@RequestBody Person person) throws InterruptedException, ExecutionException {
        System.out.println("PROCESSING STARTED");
        CompletableFuture<String> computeResult = personService.compute(person);
        computeResult.thenApply(result -> result).thenAccept(System.out::println);
        return ResponseEntity.ok(null);
    }
}
