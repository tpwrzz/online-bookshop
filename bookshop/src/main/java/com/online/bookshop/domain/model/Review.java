package com.online.bookshop.domain.model;

import java.time.LocalDate;

public class Review {
    private Long id;
    private Long userId;
    private Long bookId;
    private String reviewMessage;
    private int reviewRating; // 1–5
    private LocalDate reviewDate;

    public Review(Long id, Long userId, Long bookId, String reviewMessage,
                  int reviewRating, LocalDate reviewDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.reviewMessage = reviewMessage;
        this.reviewRating = reviewRating;
        this.reviewDate = reviewDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public LocalDate getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDate reviewDate) {
        this.reviewDate = reviewDate;
    }
}
