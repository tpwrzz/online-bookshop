package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.model.User;
import com.online.bookshop.domain.model.enums.UserStatus;
import com.online.bookshop.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> authenticate(String username, String rawPassword) {
        Optional<User> userOpt = repository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // NOTE: replace with bcrypt in production
            if (user.getPassword() != null && user.getPassword().equals(rawPassword)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<User> findByStatus(UserStatus status) {
        return repository.findByStatus(status);
    }

    public List<User> findByUsernameOrEmailContaining(String searchTerm) {
        return repository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(searchTerm, searchTerm);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public User save(User user) {
        return repository.save(user);
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return repository.findReviewsByUserId(userId);
    }
}
