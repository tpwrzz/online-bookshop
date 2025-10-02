package com.online.bookshop.infrastructure.persistence;

import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "reviews")
@Check(constraints = "review_rating >= 1 AND review_rating <= 5")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Column(name = "review_message", length = 1000, nullable = false)
    private String reviewMessage;

    @Column(name = "review_rating", nullable = false)
    private int reviewRating; // 1–5

    public ReviewEntity() {}

    public ReviewEntity(UserEntity user, BookEntity book, String reviewMessage, int reviewRating) {
        setUser(user);
        setBook(book);
        setReviewMessage(reviewMessage);
        setReviewRating(reviewRating);
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        if (!user.getReviews().contains(this)) {
            user.addReview(this); // maintain consistency
        }
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
        if (!book.getReviews().contains(this)) {
            book.addReview(this);
        }
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
        if (reviewRating < 1 || reviewRating > 5) {
            throw new IllegalArgumentException("Review rating must be between 1 and 5");
        }
        this.reviewRating = reviewRating;
    }

}
