package com.online.bookshop.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Review {
    private Long id;
    private Long userId;
    private Long bookId;
    private String reviewMessage;
    private int reviewRating;

    public Review(Long id, Long userId, Long bookId, String reviewMessage, int reviewRating) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.reviewMessage = reviewMessage;
        this.reviewRating = reviewRating;
    }
}
