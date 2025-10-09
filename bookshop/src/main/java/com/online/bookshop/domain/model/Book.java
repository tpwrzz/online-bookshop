package com.online.bookshop.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Book {
    private Long id;
    private String title;
    private Long authorId;
    private Long genreId;
    private LocalDate releaseDate;
    private Long languageId;
    private int pagesNumber;
    private double price;
    private Long currencyId;
    private Long availabilityId;
    private double averageRating;
    private List<Long> reviewIds;

    public Book(Long id, String title, Long authorId, Long genreId,
                LocalDate releaseDate, Long languageId, int pagesNumber, double price,
                Long currencyId, Long availabilityId, double averageRating, List<Long> reviewIds) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
        this.releaseDate = releaseDate;
        this.languageId = languageId;
        this.pagesNumber = pagesNumber;
        this.price = price;
        this.currencyId = currencyId;
        this.availabilityId = availabilityId;
        this.averageRating = averageRating;
        this.reviewIds = reviewIds;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getGenreId() {
        return genreId;
    }

    public void setGenreId(Long genreId) {
        this.genreId = genreId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public Long getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Long availabilityId) {
        this.availabilityId = availabilityId;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Long> getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(List<Long> reviewIds) {
        this.reviewIds = reviewIds;
    }
}
