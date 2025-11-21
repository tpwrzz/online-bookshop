package com.online.bookshop.infrastructure.persistence;

import com.online.bookshop.domain.model.enums.Availability;
import com.online.bookshop.domain.model.enums.Currency;
import com.online.bookshop.domain.model.enums.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "book")
public class BookEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private GenreEntity genre;

    @Getter
    @Column(name = "price", nullable = false)
    private double price;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 10, nullable = false)
    private Currency currency;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "language", length = 50, nullable = false)
    private Language language;

    @Getter
    @Column(name = "page_number", nullable = false)
    private int pageNumber;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "availability", length = 20, nullable = false)
    private Availability availability;

    @Getter
    @Column(name = "average_rating", nullable = false)
    private double averageRating = 0.0;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private PersonEntity author;

    @Setter
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @Setter
    @Getter
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    @Getter
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    public BookEntity() {}

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price must be positive");
        this.price = price;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber <= 0) throw new IllegalArgumentException("Page number must be positive");
        this.pageNumber = pageNumber;
    }

    public void setAverageRating(double averageRating) {
        if (averageRating < 0 || averageRating > 5) throw new IllegalArgumentException("Average rating must be between 0 and 5");
        this.averageRating = averageRating;
    }

    public List<ReviewEntity> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public void addReview(ReviewEntity review) {
        reviews.add(review);
        review.setBook(this);
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Release date cannot be in the future");
        }
        this.releaseDate = releaseDate;
    }
}
