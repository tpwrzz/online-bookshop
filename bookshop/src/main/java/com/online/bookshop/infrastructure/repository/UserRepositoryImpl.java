package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.model.User;
import com.online.bookshop.domain.model.enums.UserStatus;
import com.online.bookshop.domain.repository.UserRepository;
import com.online.bookshop.infrastructure.mapper.ReviewMapper;
import com.online.bookshop.infrastructure.mapper.UserMapper;
import com.online.bookshop.infrastructure.persistence.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaRepo;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaRepo = jpaUserRepository;
    }

    @Override
    public List<User> findAll() {
        return jpaRepo.findAll().stream()
                .map(UserMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepo.findById(id)
                .map(UserMapper::toDomain);
    }

    @Override
    public User save(User user) {
        return UserMapper.toDomain(jpaRepo.save(UserMapper.toEntity(user)));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepo.findByEmail(email).map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaRepo.findByUsername(username).map(UserMapper::toDomain);
    }

    @Override
    public List<User> findByStatus(UserStatus status) {
        return jpaRepo.findByStatus(status)
                .stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepo.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaRepo.existsByUsername(username);
    }

    @Override
    public List<User> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String usernamePart, String emailPart) {
        return jpaRepo.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(usernamePart, emailPart)
                .stream()
                .map(UserMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> findReviewsByUserId(Long userId) {
        return jpaRepo.findById(userId)
                .map(UserEntity::getReviews)
                .orElse(List.of())
                .stream()
                .map(ReviewMapper::toDomain)
                .toList();
    }

}
