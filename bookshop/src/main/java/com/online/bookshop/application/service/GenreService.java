package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Genre;
import com.online.bookshop.domain.repository.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository repository;

    public GenreService(GenreRepository repository) {
        this.repository = repository;
    }

    public List<Genre> findAll() {
        List<Genre> all = repository.findAll();
        System.out.println("Genres in DB: " + all.size());
        return repository.findAll();
    }

    public Optional<Genre> findById(Long id) {
        return repository.findById(id);
    }

    public List<Genre> findByLastName(String lastName) {
        return repository.findByName(lastName);
    }

    public Genre save(Genre genre) {
        return repository.save(genre);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
