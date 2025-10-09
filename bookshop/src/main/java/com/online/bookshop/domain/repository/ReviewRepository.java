package com.online.bookshop.domain.repository;

import com.online.bookshop.domain.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    List<Review> findAll();

    Optional<Review> findById(Long id);

    Review save(Review review);

    void deleteById(Long id);

    List<Review> findByBookId(Long bookId);

    List<Review> findByUserId(Long userId);
}
