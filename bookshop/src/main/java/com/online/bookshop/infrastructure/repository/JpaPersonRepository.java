package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.infrastructure.persistence.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPersonRepository extends JpaRepository<PersonEntity, Long> {
    List<PersonEntity> findByLastNameContainingIgnoreCase(String lastName);
}
