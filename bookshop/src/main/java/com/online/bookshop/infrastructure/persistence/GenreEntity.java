package com.online.bookshop.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "genre")
public class GenreEntity {
    // --- Getters and Setters ---
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    public GenreEntity() {
    }
}
