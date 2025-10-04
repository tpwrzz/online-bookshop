package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.infrastructure.persistence.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaGenreRepository extends JpaRepository<GenreEntity, Long> {
    List<GenreEntity> findByNameIgnoreCase(String Name);
}
