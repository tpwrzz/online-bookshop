package com.online.bookshop.infrastructure.persistence;

import com.online.bookshop.domain.model.enums.UserStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private UserStatus status;

    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private PersonEntity person;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews = new ArrayList<>();

    public UserEntity() {}

    public UserEntity(UserStatus status, String email, LocalDate registrationDate, PersonEntity person) {
        this.status = status;
        this.email = email;
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

    public void removeReview(ReviewEntity review) {
        reviews.remove(review);
        review.setUser(null);
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
}
