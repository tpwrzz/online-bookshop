package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Person;
import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.model.User;
import com.online.bookshop.domain.model.enums.UserStatus;
import com.online.bookshop.domain.repository.PersonRepository;
import com.online.bookshop.domain.repository.UserRepository;
import com.online.bookshop.infrastructure.mapper.PersonMapper;
import com.online.bookshop.infrastructure.mapper.UserMapper;
import com.online.bookshop.infrastructure.persistence.PersonEntity;
import com.online.bookshop.infrastructure.persistence.UserEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private final PersonRepository personRepository;

    public UserService(UserRepository repository, PersonRepository personRepository) {
        this.repository = repository;
        this.personRepository = personRepository;
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


    public User signup(User toCreate) {
        // minimal checks
        if (toCreate.getUsername() == null || toCreate.getPassword() == null || toCreate.getEmail() == null) {
            throw new IllegalArgumentException("username, email and password required");
        }
        if (repository.existsByUsername(toCreate.getUsername()) || repository.existsByEmail(toCreate.getEmail())) {
            throw new IllegalArgumentException("user exists");
        }
        toCreate.setRegistrationDate(LocalDate.now());
        toCreate.setStatus(UserStatus.ACTIVE);

        UserEntity entity = UserMapper.toEntity(toCreate);

        Person personDto = personRepository.findById(toCreate.getPersonId())
                .orElseThrow(() -> new IllegalArgumentException("Person not found"));

        PersonEntity personEntity = PersonMapper.toEntity(personDto);

// assign the entity
        entity.setPerson(personEntity);

// save the entity — cascade takes care of PersonEntity if needed
        User savedEntity = repository.save(UserMapper.toDomain(entity));
        System.out.println(savedEntity.getPersonId());
        return savedEntity;
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

    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    public List<User> findByUsernameOrEmailContaining(String searchTerm) {
        return repository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(searchTerm, searchTerm);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public User save(User user) {
        UserEntity entity = UserMapper.toEntity(user);
        Optional<Person> author = personRepository.findById(user.getPersonId());
        entity.setPerson(PersonMapper.toEntity(Objects.requireNonNull(author.orElse(null))));
        return repository.save(UserMapper.toDomain(entity));
    }

    public List<Review> getReviewsByUserId(Long userId) {
        return repository.findReviewsByUserId(userId);
    }
}
