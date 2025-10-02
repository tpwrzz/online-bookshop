package com.online.bookshop.domain.model;

import java.time.LocalDate;

public class User {
    private Long id;
    private Long personId;
    private Long statusId;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private String username;

    public User(Long id, Long personId, Long statusId, String email,
                String password, LocalDate registrationDate, String username) {
        this.id = id;
        this.personId = personId;
        this.statusId = statusId;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
