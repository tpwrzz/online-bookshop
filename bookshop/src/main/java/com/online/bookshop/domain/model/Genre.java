package com.online.bookshop.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Genre {
    private Long id;
    private String name;

    public Genre(Long id, String genreName) {
        this.id = id;
        this.name = genreName;
    }
}
