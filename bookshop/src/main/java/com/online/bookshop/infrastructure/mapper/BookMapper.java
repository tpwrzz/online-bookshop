package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.Book;
import com.online.bookshop.domain.model.enums.Availability;
import com.online.bookshop.domain.model.enums.Currency;
import com.online.bookshop.domain.model.enums.Language;
import com.online.bookshop.infrastructure.persistence.BookEntity;
import com.online.bookshop.infrastructure.persistence.ReviewEntity;

import java.util.stream.Collectors;

public class BookMapper {

    public static Book toDomain(BookEntity entity) {
        if (entity == null) return null;

        Book domain = new Book();
        domain.setId(entity.getId());
        domain.setTitle(entity.getTitle());
        domain.setGenreId(entity.getGenre().getId());
        domain.setAuthorId(entity.getAuthor().getId());
        domain.setLanguageId(entity.getLanguage().ordinal() + 1L); // adjust if needed
        domain.setAvailabilityId(entity.getAvailability().ordinal() + 1L);
        domain.setCurrencyId(entity.getCurrency().ordinal() + 1L);
        domain.setPrice(entity.getPrice());
        domain.setPagesNumber(entity.getPageNumber());
        domain.setAverageRating(entity.getAverageRating());
        domain.setReleaseDate(entity.getReleaseDate());
        domain.setReviewIds(
                entity.getReviews()
                        .stream()
                        .map(ReviewEntity::getId)
                        .collect(Collectors.toList())
        );
        return domain;
    }

    public static BookEntity toEntity(Book domain) {
        if (domain == null) return null;

        BookEntity entity = new BookEntity();
        entity.setTitle(domain.getTitle());
        entity.setLanguage(Language.values()[domain.getLanguageId().intValue() - 1]);
        entity.setCurrency(Currency.values()[domain.getCurrencyId().intValue() - 1]);
        entity.setAvailability(Availability.values()[domain.getAvailabilityId().intValue() - 1]);
        entity.setPrice(domain.getPrice());
        entity.setPageNumber(domain.getPagesNumber());
        entity.setAverageRating(domain.getAverageRating());
        entity.setReleaseDate(domain.getReleaseDate());

        return entity;
    }
}
