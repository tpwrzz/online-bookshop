package com.online.bookshop.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Person {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String address;
    private String telephone;
    private LocalDate birthDate;

    public Person(Long id, String firstName, String middleName, String lastName,
                   String address, String telephone, LocalDate birthDate) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
        this.birthDate = birthDate;
    }
}
