package com.online.bookshop.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

@Getter
@Entity
@Table(name = "reviews")
@Check(constraints = "review_rating >= 1 AND review_rating <= 5")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private BookEntity book;

    @Setter
    @Column(name = "review_message", length = 1000, nullable = false)
    private String reviewMessage;

    @Column(name = "review_rating", nullable = false)
    private int reviewRating; // 1–5

    public ReviewEntity() {}

    public void setUser(UserEntity user) {
        this.user = user;
        if (!user.getReviews().contains(this)) {
            user.addReview(this); // maintain consistency
        }
    }

    public void setBook(BookEntity book) {
        this.book = book;
        if (!book.getReviews().contains(this)) {
            book.addReview(this);
        }
    }

    public void setReviewRating(int reviewRating) {
        if (reviewRating < 1 || reviewRating > 5) {
            throw new IllegalArgumentException("Review rating must be between 1 and 5");
        }
        this.reviewRating = reviewRating;
    }

}
