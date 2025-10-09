package com.online.bookshop.domain.repository;

import com.online.bookshop.domain.model.Book;
import com.online.bookshop.domain.model.Review;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    List<Book> findByTitle(String Title);

    Book save(Book book);

    void deleteById(Long id);

    int bookCount();

    List<Book> findByGenre(String GenreName);

    List<Book> findByAuthor(String Author);

    List<Book> findByYear(int Year);

    List<Book> findByTitleContaining(String Title);

    List<Book> sortByPrice();

    Book updatePrice(long BookId, double Price);

    List<Review> findReviewsByBookId(Long bookId);

    Optional<Double> findAverageRatingByBookId(Long bookId);

    List<Book> findAllSortedByAverageRating();

    List<Review> findReviewsByBookIdAndRating(Long bookId, double rating);
}
