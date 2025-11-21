package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.infrastructure.persistence.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaGenreRepository extends JpaRepository<GenreEntity, Long> {
    List<GenreEntity> findByNameIgnoreCase(String Name);
}
