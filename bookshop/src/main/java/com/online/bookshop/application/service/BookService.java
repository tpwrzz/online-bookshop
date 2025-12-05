package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Book;
import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public int bookCount() {
        return repository.bookCount();
    }

    public List<Book> findByGenre(String genreName) {
        return repository.findByGenre(genreName);
    }

    public List<Book> findByAuthor(String authorName) {
        return repository.findByAuthor(authorName);
    }

    public List<Book> findByYear(int year) {
        return repository.findByYear(year);
    }

    public List<Book> findByTitleContaining(String keyword) {
        return repository.findByTitleContaining(keyword);
    }

    public List<Book> sortByPrice() {
        return repository.sortByPrice();
    }

    public List<Review> getReviewsByBookId(Long bookId) {
        return repository.findReviewsByBookId(bookId);
    }

    public Optional<Double> getAverageRatingByBookId(Long bookId) {
        return repository.findAverageRatingByBookId(bookId);
    }

    public List<Book> getBooksSortedByAverageRating() {
        return repository.findAllSortedByAverageRating();
    }

    public List<Review> getReviewsByBookIdAndRating(Long bookId, double rating) {
        return repository.findReviewsByBookIdAndRating(bookId, rating);
    }
}
