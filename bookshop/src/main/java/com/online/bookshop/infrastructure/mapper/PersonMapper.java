package com.online.bookshop.infrastructure.mapper;


import com.online.bookshop.domain.model.Person;
import com.online.bookshop.infrastructure.persistence.PersonEntity;

public class PersonMapper {

    public static Person toDomain(PersonEntity entity) {
        return new Person(
                entity.getId(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getAddress(),
                entity.getTelephoneNumber(),
                entity.getBirthDate()
        );
    }

    public static PersonEntity toEntity(Person person) {
        PersonEntity entity = new PersonEntity();
        entity.setFirstName(person.getFirstName());
        entity.setMiddleName(person.getMiddleName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setTelephoneNumber(person.getTelephone());
        entity.setBirthDate(person.getBirthDate());
        return entity;
    }
}
