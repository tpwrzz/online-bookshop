package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.Book;
import com.online.bookshop.infrastructure.persistence.BookEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookMapper {
    public static Book toDomain(BookEntity entity) {
        if (entity == null) return null;

        Book book = new Book();
        book.setId(entity.getId());
        book.setTitle(entity.getTitle());
        book.setGenre(GenreMapper.toDomain(entity.getGenre()));
        book.setAuthor(PersonMapper.toDomain(entity.getAuthor()));
        book.setLanguage(entity.getLanguage());
        book.setCurrency(entity.getCurrency());
        book.setAvailability(entity.getAvailability());
        book.setPrice(entity.getPrice());
        book.setPagesNumber(entity.getPageNumber());
        book.setAverageRating(entity.getAverageRating());
        book.setReleaseDate(entity.getReleaseDate());

        book.setReviews(
                entity.getReviews()
                        .stream()
                        .map(ReviewMapper::toDomain)
                        .collect(Collectors.toList())
        );

        return book;
    }

    public static BookEntity toEntity(Book book) {
        if (book == null) return null;

        BookEntity entity = new BookEntity();
        entity.setTitle(book.getTitle());
        entity.setGenre(GenreMapper.toEntity(book.getGenre()));
        entity.setAuthor(PersonMapper.toEntity(book.getAuthor()));
        entity.setLanguage(book.getLanguage());
        entity.setCurrency(book.getCurrency());
        entity.setAvailability(book.getAvailability());
        entity.setPrice(book.getPrice());
        entity.setPageNumber(book.getPagesNumber());
        entity.setAverageRating(book.getAverageRating());
        entity.setReleaseDate(book.getReleaseDate());

        if (book.getReviews() != null) {
            entity.setReviews(
                    book.getReviews()
                            .stream()
                            .map(ReviewMapper::toEntity)
                            .collect(Collectors.toList())
            );
        }

        return entity;
    }
}
