package com.online.bookshop.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "genre")
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100, nullable = false, unique = true)
    private String name;

    public GenreEntity() {
    }

    public GenreEntity(String name) {
        this.name = name;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
