package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.infrastructure.persistence.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByBook_Id(Long bookId);

    List<ReviewEntity> findByUser_Id(Long userId);
}
