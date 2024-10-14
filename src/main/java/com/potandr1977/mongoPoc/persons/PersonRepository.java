package com.potandr1977.mongoPoc.persons;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    Flux<Person> findByNameContaining(String name);
}
