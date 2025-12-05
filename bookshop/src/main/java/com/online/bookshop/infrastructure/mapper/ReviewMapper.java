package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.Review;
import com.online.bookshop.infrastructure.persistence.ReviewEntity;
import org.springframework.stereotype.Component;

@Component
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
        if (domain.getId() != 0) entity.setId(domain.getId());
        entity.setUser(UserMapper.refUser(domain.getUserId()));
        entity.setBook(BookMapper.refBook(domain.getBookId()));
        entity.setReviewMessage(domain.getReviewMessage());
        entity.setReviewRating(domain.getReviewRating());
        return entity;
    }
}
