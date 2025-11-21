package com.online.bookshop.domain.model;

import com.online.bookshop.domain.model.enums.Availability;
import com.online.bookshop.domain.model.enums.Currency;
import com.online.bookshop.domain.model.enums.Language;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class Book {
    private Long id;
    private String title;
    private Person author;
    private Genre genre;
    private LocalDate releaseDate;
    private Language language;
    private int pagesNumber;
    private double price;
    private Currency currency;
    private Availability availability;
    private double averageRating;
    private List<Review> reviews;

    public Book() {
    }

}
