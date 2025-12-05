package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.domain.model.Person;
import com.online.bookshop.domain.repository.PersonRepository;
import com.online.bookshop.infrastructure.mapper.PersonMapper;
import com.online.bookshop.infrastructure.persistence.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final JpaPersonRepository jpaRepo;

    @Override
    public List<Person> findAll() {
        return jpaRepo.findAll().stream()
                .map(PersonMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Person> findById(Long id) {
        return jpaRepo.findById(id)
                .map(PersonMapper::toDomain);
    }

    @Override
    public List<Person> findByLastName(String lastName) {
        return jpaRepo.findByLastNameContainingIgnoreCase(lastName).stream()
                .map(PersonMapper::toDomain)
                .toList();
    }

    @Override
    public Person save(Person person) {
        PersonEntity entity = jpaRepo.save(PersonMapper.toEntity(person));
        return PersonMapper.toDomain(entity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }
}
