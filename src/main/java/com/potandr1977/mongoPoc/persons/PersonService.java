package com.potandr1977.mongoPoc.persons;

import com.potandr1977.mongoPoc.persons.entities.Account;
import com.potandr1977.mongoPoc.persons.entities.Payment;
import com.potandr1977.mongoPoc.persons.entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Flux<Person> findAll(){
        return personRepository.findAll().doOnError(err-> {
            var m =  err.getMessage();
        });
    }

    public Mono<Person> findById(String id){
        return personRepository.findById(id);
    }

    public Flux<Person> findByNameContaining(String name){
        return personRepository.findByNameContaining(name);
    }

    public Mono<Person> save(Person person){
        return personRepository.save(person);
    }

    public Mono<Person> update(Person person) {
        return personRepository.findById(person.getId()).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalPerson -> {
                    if (optionalPerson.isPresent()) {
                        return personRepository.save(person);
                    }

                    return Mono.empty();
                });
    }

    public Mono<Void> deleteById(String id) {
        return personRepository.deleteById(id);
    }

    public Mono<Void> deleteAll() {
        return personRepository.deleteAll();
    }

    public Mono<Person> AddAccount(String personId, String accountName){
        return personRepository.findById(personId).flatMap(person ->{
            person.addAccount(Account.create(accountName,null));

            return personRepository.save(person);
        });
    }

    public Mono<Person> addPayment(String personId, String accountId, Payment payment){
       return personRepository.findById(personId).flatMap(person ->{
           person.addPayment(accountId, payment);

           return personRepository.save(person);
        });
    }

/*
    @KafkaListener(topics="save-person-topic", groupId="java-service-group")
    void listen(String data) {
        var m = data;
    }
 */
}
