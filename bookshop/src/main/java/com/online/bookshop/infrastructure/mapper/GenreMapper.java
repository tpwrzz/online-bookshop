package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.Genre;
import com.online.bookshop.infrastructure.persistence.GenreEntity;

public class GenreMapper {
    public static Genre toDomain(GenreEntity entity) {
        return new Genre(
                entity.getId(),
                entity.getName()
        );
    }

    public static GenreEntity toEntity(Genre genre) {
        GenreEntity entity = new GenreEntity();
        entity.setName(genre.getName());
        return entity;
    }
}
