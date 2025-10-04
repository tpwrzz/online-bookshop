package com.online.bookshop.domain.model;

public class Genre {
    private Long id;
    private String name;

    public Genre() {
    }
    public Genre(Long id, String genreName) {
        this.id = id;
        this.name = genreName;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
