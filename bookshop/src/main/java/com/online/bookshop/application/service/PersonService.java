package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Person;
import com.online.bookshop.domain.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findAll() {
        return repository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return repository.findById(id);
    }

    public List<Person> findByLastName(String lastName) {
        return repository.findByLastName(lastName);
    }

    public Person save(Person person) {
        return repository.save(person);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
