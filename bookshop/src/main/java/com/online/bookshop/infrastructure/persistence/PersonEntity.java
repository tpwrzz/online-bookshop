package com.online.bookshop.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "persons")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "telephone_number", length = 20, unique = true)
    private String telephoneNumber;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookEntity> authoredBooks = new ArrayList<>();

    public PersonEntity() {}

    public PersonEntity(String firstName, String middleName, String lastName,
                        String address, String telephoneNumber, LocalDate birthDate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<BookEntity> getAuthoredBooks() {
        return Collections.unmodifiableList(authoredBooks);
    }

    public void addAuthoredBook(BookEntity book) {
        authoredBooks.add(book);
        book.setAuthor(this);
    }

    public void removeAuthoredBook(BookEntity book) {
        authoredBooks.remove(book);
        book.setAuthor(null);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setAuthoredBooks(List<BookEntity> authoredBooks) {
        this.authoredBooks = authoredBooks;
    }
}
