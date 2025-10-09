package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Book;
import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.model.User;
import com.online.bookshop.domain.repository.BookRepository;
import com.online.bookshop.domain.repository.ReviewRepository;
import com.online.bookshop.domain.repository.UserRepository;
import com.online.bookshop.infrastructure.mapper.BookMapper;
import com.online.bookshop.infrastructure.mapper.ReviewMapper;
import com.online.bookshop.infrastructure.mapper.UserMapper;
import com.online.bookshop.infrastructure.persistence.ReviewEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository repository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;


    public ReviewService(ReviewRepository repository, UserRepository userRepository, BookRepository bookRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public List<Review> findAll() {
        return repository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return repository.findById(id);
    }

    public Review save(Review review) {
        ReviewEntity entity = ReviewMapper.toEntity(review);
        Optional<User> author = userRepository.findById(review.getUserId());
        entity.setUser(Objects.requireNonNull(UserMapper.toEntity(author.orElse(null))));
        Optional<Book> book = bookRepository.findById(review.getBookId());
        entity.setBook(Objects.requireNonNull(BookMapper.toEntity(book.orElse(null))));
        return repository.save(ReviewMapper.toDomain(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Review> findByBookId(Long bookId) {
        return repository.findByBookId(bookId);
    }

    public List<Review> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}
