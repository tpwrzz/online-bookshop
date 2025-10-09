package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.Review;
import com.online.bookshop.infrastructure.persistence.ReviewEntity;

public class ReviewMapper {
    public static Review toDomain(ReviewEntity entity) {
        if (entity == null) return null;

        return new Review(
                entity.getId(),
                entity.getUser() != null ? entity.getUser().getId() : null,
                entity.getBook() != null ? entity.getBook().getId() : null,
                entity.getReviewMessage(),
                entity.getReviewRating()
        );
    }

    public static ReviewEntity toEntity(Review domain) {
        if (domain == null) return null;

        ReviewEntity entity = new ReviewEntity();
        entity.setReviewMessage(domain.getReviewMessage());
        entity.setReviewRating(domain.getReviewRating());

        // user and book mapping should be handled externally
        // entity.setUser(UserMapper.toEntity(domain.getUser()));
        // entity.setBook(BookMapper.toEntity(domain.getBook()));

        return entity;
    }
}
