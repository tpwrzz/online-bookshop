package com.online.bookshop.domain.repository;

import com.online.bookshop.domain.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();
    Optional<Genre> findById(Long id);
    List<Genre> findByName(String Name);
    Genre save(Genre genre);
    void deleteById(Long id);
}
