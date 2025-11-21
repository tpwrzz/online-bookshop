package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.domain.model.enums.UserStatus;
import com.online.bookshop.infrastructure.persistence.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByStatus(UserStatus status);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<UserEntity> findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(String usernamePart, String emailPart);
}

