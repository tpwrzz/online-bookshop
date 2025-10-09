package com.online.bookshop.infrastructure.persistence;

import com.online.bookshop.domain.model.enums.Availability;
import com.online.bookshop.domain.model.enums.Currency;
import com.online.bookshop.domain.model.enums.Language;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private GenreEntity genre;

    @Column(name = "price", nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 10, nullable = false)
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "language", length = 50, nullable = false)
    private Language language;

    @Column(name = "page_number", nullable = false)
    private int pageNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", length = 20, nullable = false)
    private Availability availability;

    @Column(name = "average_rating", nullable = false)
    private double averageRating = 0.0;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private PersonEntity author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
    public BookEntity() {}

    public BookEntity(String title, GenreEntity genre, double price, Currency currency, Language language,
                      int pageNumber, Availability availability, PersonEntity author, LocalDate releaseDate) {
        this.title = title;
        this.genre = genre;
        setPrice(price);
        this.currency = currency;
        this.language = language;
        setPageNumber(pageNumber);
        this.availability = availability;
        this.author = author;
        setReleaseDate(releaseDate);
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public GenreEntity getGenre() {
        return genre;
    }

    public void setGenre(GenreEntity genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price must be positive");
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Language getLanguage() {
        return language;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        if (pageNumber <= 0) throw new IllegalArgumentException("Page number must be positive");
        this.pageNumber = pageNumber;
    }

    public Availability getAvailability() {
        return availability;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        if (averageRating < 0 || averageRating > 5) throw new IllegalArgumentException("Average rating must be between 0 and 5");
        this.averageRating = averageRating;
    }

    public PersonEntity getAuthor() {
        return author;
    }

    public List<ReviewEntity> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public void addReview(ReviewEntity review) {
        reviews.add(review);
        review.setBook(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public void setAuthor(PersonEntity author) {
        this.author = author;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public List<OrderItemEntity> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemEntity> orderItems) {
        this.orderItems = orderItems;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        if (releaseDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Release date cannot be in the future");
        }
        this.releaseDate = releaseDate;
    }
}
