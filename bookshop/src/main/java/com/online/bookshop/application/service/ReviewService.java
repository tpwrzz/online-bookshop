package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository repository;

    public ReviewService(ReviewRepository repository) {
        this.repository = repository;
    }

    public List<Review> findAll() {
        return repository.findAll();
    }

    public Optional<Review> findById(Long id) {
        return repository.findById(id);
    }

    public Review save(Review review) {
        return repository.save(review);
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
