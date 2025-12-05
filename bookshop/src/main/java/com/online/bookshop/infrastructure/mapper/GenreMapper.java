package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.Genre;
import com.online.bookshop.infrastructure.persistence.GenreEntity;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    public static Genre toDomain(GenreEntity entity) {
        return new Genre(
                entity.getId(),
                entity.getName()
        );
    }

    public static GenreEntity toEntity(Genre genre) {
        GenreEntity entity = new GenreEntity();
        if (genre.getId() != 0) entity.setId(genre.getId());
        entity.setName(genre.getName());
        return entity;
    }

    public static GenreEntity ref(Long genreId) {
        if (genreId == null) return null;

        GenreEntity entity = new GenreEntity();
        // Устанавливаем только ID — этого достаточно
        entity.setId(genreId);
        return entity;
    }
}
