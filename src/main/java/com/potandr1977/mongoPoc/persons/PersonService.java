package com.potandr1977.mongoPoc.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Flux<Person> findAll(){
        return personRepository.findAll();
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

    public Mono<Person> update(String id, Person Person) {
        return personRepository.findById(id).map(Optional::of).defaultIfEmpty(Optional.empty())
                .flatMap(optionalPerson -> {
                    if (optionalPerson.isPresent()) {
                        Person.setId(id);
                        return personRepository.save(Person);
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
}
