package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.repository.ReviewRepository;
import com.online.bookshop.infrastructure.mapper.ReviewMapper;
import com.online.bookshop.infrastructure.persistence.ReviewEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    private final JpaReviewRepository jpaRepo;

    public ReviewRepositoryImpl(JpaReviewRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<Review> findAll() {
        return jpaRepo.findAll().stream()
                .map(ReviewMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return jpaRepo.findById(id)
                .map(ReviewMapper::toDomain);
    }

    @Override
    public Review save(Review review) {
        ReviewEntity entity = jpaRepo.save(ReviewMapper.toEntity(review));
        return ReviewMapper.toDomain(entity);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public List<Review> findByBookId(Long bookId) {
        return jpaRepo.findByBook_Id(bookId).stream()
                .map(ReviewMapper::toDomain)
                .toList();
    }

    @Override
    public List<Review> findByUserId(Long userId) {
        return jpaRepo.findByUser_Id(userId).stream()
                .map(ReviewMapper::toDomain)
                .toList();
    }
}
