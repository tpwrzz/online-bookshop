package com.online.bookshop.infrastructure.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Entity
@Table(name = "persons")
public class PersonEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Getter
    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Getter
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;

    @Getter
    @Column(name = "address")
    private String address;

    @Getter
    @Column(name = "telephone_number", length = 20, unique = true)
    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{7,15}$",
            message = "Telephone number must be a valid format (e.g. +123456789 or 1234567890)"
    )
    private String telephoneNumber;

    @Getter
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    public PersonEntity() {}
}
