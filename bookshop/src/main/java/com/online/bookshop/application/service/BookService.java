package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Book;
import com.online.bookshop.domain.model.Genre;
import com.online.bookshop.domain.model.Person;
import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.repository.BookRepository;
import com.online.bookshop.domain.repository.GenreRepository;
import com.online.bookshop.domain.repository.PersonRepository;
import com.online.bookshop.infrastructure.mapper.BookMapper;
import com.online.bookshop.infrastructure.mapper.GenreMapper;
import com.online.bookshop.infrastructure.mapper.PersonMapper;
import com.online.bookshop.infrastructure.persistence.BookEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;
    private final GenreRepository genreRepository;
    private final PersonRepository authorRepository;

    public BookService(BookRepository bookRepository,
                       GenreRepository genreRepository,
                       PersonRepository authorRepository) {
        this.repository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Optional<Book> findById(Long id) {
        return repository.findById(id);
    }

    public List<Book> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Book save(Book domain) {
        BookEntity entity = BookMapper.toEntity(domain);

        Optional<Genre> genre = genreRepository.findById(domain.getGenreId());
        Optional<Person> author = authorRepository.findById(domain.getAuthorId());

        entity.setGenre(GenreMapper.toEntity(Objects.requireNonNull(genre.orElse(null))));
        entity.setAuthor(PersonMapper.toEntity(Objects.requireNonNull(author.orElse(null))));

        return repository.save(BookMapper.toDomain(entity));
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

    public Book updatePrice(Long id, double newPrice) {
        return repository.updatePrice(id, newPrice);
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
