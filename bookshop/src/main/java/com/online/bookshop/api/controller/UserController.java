package com.online.bookshop.api.controller;

import com.online.bookshop.api.dto.LoginRequest;
import com.online.bookshop.api.dto.TokenResponse;
import com.online.bookshop.api.security.JwtUtil;
import com.online.bookshop.application.service.PersonService;
import com.online.bookshop.application.service.UserService;
import com.online.bookshop.domain.model.Review;
import com.online.bookshop.domain.model.User;
import com.online.bookshop.domain.model.enums.UserStatus;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil, PersonService personService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @PermitAll
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        return userOpt.map(user -> {
            String token = jwtUtil.generateToken(user.getUsername(), user.getId());
            return ResponseEntity.ok(new TokenResponse(token));
        }).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

//    @PostMapping("/signup")
//    @PermitAll
//    public ResponseEntity<User> signup(@RequestBody SignupRequest req) {
//        try {
//            User toCreate = new User();
//            toCreate.setUsername(req.getUsername());
//            toCreate.setEmail(req.getEmail());
//            toCreate.setPassword(req.getPassword()); // hash in production
//            Person person = new Person();
//            person.setAddress(req.getAddress());
//            person.setTelephone(req.getTelephone());
//            person.setFirstName(req.getFirstName());
//            person.setLastName(req.getLastName());
//            person.setMiddleName(req.getMiddleName());
//            person.setBirthDate(req.getBirthDate());
//            Person newPerson = personService.save(person);
//            toCreate.setPersonId(newPerson.getId());
//            System.out.println(newPerson.getId());
//            User created = userService.signup(toCreate);
//            return ResponseEntity.status(HttpStatus.CREATED).body(created);
//        } catch (IllegalArgumentException ex) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).build();
//        }
//    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getCurrentUserInfo(Authentication auth) {
        String username = auth.getName();
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        Optional<User> user = userService.findByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status")
    public ResponseEntity<List<User>> getUsersByStatus(@RequestParam UserStatus status) {
        List<User> users = userService.findByStatus(status);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam String query) {
        List<User> users = userService.findByUsernameOrEmailContaining(query);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userService.existsByEmail(user.getEmail()) || userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id); // ensure correct ID
        User updatedUser = userService.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<Review>> getReviewsByUser(@PathVariable Long id) {
        List<Review> reviews = userService.getReviewsByUserId(id);
        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reviews);
    }
}
