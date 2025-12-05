package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.domain.model.Genre;
import com.online.bookshop.domain.repository.GenreRepository;
import com.online.bookshop.infrastructure.mapper.GenreMapper;
import com.online.bookshop.infrastructure.persistence.GenreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreRepositoryImpl implements GenreRepository {

    private final JpaGenreRepository jpaRepo;

    @Override
    public List<Genre> findAll() {
        return jpaRepo.findAll().stream()
                .map(GenreMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return jpaRepo.findById(id)
                .map(GenreMapper::toDomain);
    }

    @Override
    public List<Genre> findByName(String Name) {
        return jpaRepo.findByNameContainingIgnoreCase(Name).stream()
                .map(GenreMapper::toDomain)
                .toList();
    }


    @Override
    public Genre save(Genre genre) {
        GenreEntity entity = jpaRepo.save(GenreMapper.toEntity(genre));
        return GenreMapper.toDomain(entity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }
}
