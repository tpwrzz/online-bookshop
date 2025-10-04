package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.infrastructure.persistence.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaPersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByLastNameIgnoreCase(String lastName);
}
