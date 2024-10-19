package com.potandr1977.mongoPoc.persons;

import an.awesome.pipelinr.Pipeline;
import an.awesome.pipelinr.Pipelinr;
import com.potandr1977.mongoPoc.persons.dtos.AddAccountDto;
import com.potandr1977.mongoPoc.persons.dtos.PersonDto;
import com.potandr1977.mongoPoc.persons.entities.Person;
import com.potandr1977.mongoPoc.persons.handlers.PingCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Stream;

//@CrossOrigin(origins = "http://localhost:8099")
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonService personService;
    @Autowired
    Pipeline pipeline;

    @GetMapping("/ping")
    @ResponseStatus(HttpStatus.OK)
    public String getPing() {
        return pipeline.send(new PingCommand("ping"));
    }

    @GetMapping("/persons/all")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> getPersons(@RequestParam(required = false) String title) {
        if (title == null)
            return personService.findAll();
        else
            return personService.findByNameContaining(title);
    }

    @GetMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Person> getPersonById(@PathVariable("id") String id) {
        return personService.findById(id);
    }

    @PostMapping("/persons")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> createPerson(@RequestBody PersonDto personDto) {
        return personService.save(Person.create(personDto.name(), personDto.inn(),null));
    }

    @PutMapping("/persons")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Person> updatePerson(@RequestBody Person Person) {
        return personService.update(Person);
    }

    @DeleteMapping("/persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePerson(@PathVariable("id") String id) {
        return personService.deleteById(id);
    }

    @DeleteMapping("/persons")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllPersons() {
        return personService.deleteAll();
    }

    @GetMapping("/persons/name")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> findByNameContaining(@RequestParam String name) {
        return personService.findByNameContaining(name);
    }

    @PostMapping("/persons/addaccount")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Person> addAccount(@RequestBody AddAccountDto addAccountDto) {
        return personService.AddAccount(addAccountDto.personId(),addAccountDto.accountName());
    }
}
