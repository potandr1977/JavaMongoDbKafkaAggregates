package com.potandr1977.mongoPoc.persons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@CrossOrigin(origins = "http://localhost:8099")
@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("/Persons")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> getAllPersons(@RequestParam(required = false) String title) {
        if (title == null)
            return personService.findAll();
        else
            return personService.findByNameContaining(title);
    }

    @GetMapping("/Persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Person> getPersonById(@PathVariable("id") String id) {
        return personService.findById(id);
    }

    @PostMapping("/Persons")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> createPerson(@RequestBody Person person) {
        return personService.save(Person.create(person.getName(), person.getInn(),null));
    }

    @PutMapping("/Persons/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Person> updatePerson(@PathVariable("id") String id, @RequestBody Person Person) {
        return personService.update(id, Person);
    }

    @DeleteMapping("/Persons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePerson(@PathVariable("id") String id) {
        return personService.deleteById(id);
    }

    @DeleteMapping("/Persons")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllPersons() {
        return personService.deleteAll();
    }

    @GetMapping("/Persons/name")
    @ResponseStatus(HttpStatus.OK)
    public Flux<Person> findByNameContaining(@RequestParam String name) {
        return personService.findByNameContaining(name);
    }
}
