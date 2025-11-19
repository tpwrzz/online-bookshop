package com.online.bookshop.infrastructure.persistence;

import com.online.bookshop.domain.model.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"}), @UniqueConstraint(columnNames = {"username"})})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private UserStatus status;

    @Email
    @NotBlank
    @Size(max = 100)
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, numbers, dots, underscores and hyphens.")
    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @NotBlank
    @Size(min = 8, max = 64)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$", message = "Password must be 8–64 characters, include uppercase, lowercase, digit, and special character.")
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private PersonEntity person;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<>();

    public UserEntity() {
    }

    public UserEntity(UserStatus status, String email, String username, String password, LocalDate registrationDate, PersonEntity person) {
        this.status = status;
        this.email = email;
        setPassword(password);
        setUsername(username);
        this.registrationDate = registrationDate;
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public UserStatus getStatus() {
        return status;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public List<OrderEntity> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public void addOrder(OrderEntity order) {
        orders.add(order);
        order.setUser(this);
    }

    public void removeOrder(OrderEntity order) {
        orders.remove(order);
        order.setUser(null);
    }

    public List<ReviewEntity> getReviews() {
        return Collections.unmodifiableList(reviews);
    }

    public void addReview(ReviewEntity review) {
        reviews.add(review);
        review.setUser(this);
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public @NotBlank @Size(min = 3, max = 50) @Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Username can only contain letters, numbers, dots, underscores and hyphens.") String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || !username.matches("^[a-zA-Z0-9._-]{3,50}$")) {
            throw new IllegalArgumentException("Invalid username format.");
        }
        this.username = username;
    }

    public @NotBlank @Size(min = 8, max = 64) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$", message = "Password must be 8–64 characters, include uppercase, lowercase, digit, and special character.") String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
