package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.User;
import com.online.bookshop.infrastructure.persistence.UserEntity;

import java.util.stream.Collectors;

public class UserMapper {

    public static User toDomain(UserEntity entity) {
        if (entity == null) return null;

        User domain = new User();
        domain.setId(entity.getId());
        domain.setStatus(entity.getStatus());
        domain.setEmail(entity.getEmail());
        domain.setUsername(entity.getUsername());
        domain.setPassword(entity.getPassword());
        domain.setRegistrationDate(entity.getRegistrationDate());

        if (entity.getPerson() != null) {
            domain.setPersonId(entity.getPerson().getId());
        }

        domain.setOrders(
                entity.getOrders()
                        .stream()
                        .map(OrderMapper::toDomain)
                        .collect(Collectors.toList())
        );

        domain.setReviews(
                entity.getReviews()
                        .stream()
                        .map(ReviewMapper::toDomain)
                        .collect(Collectors.toList())
        );

        return domain;
    }

    public static UserEntity toEntity(User domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity();
        entity.setStatus(domain.getStatus());
        entity.setEmail(domain.getEmail());
        entity.setUsername(domain.getUsername());
        entity.setPassword(domain.getPassword());
        entity.setRegistrationDate(domain.getRegistrationDate());

        entity.setOrders(
                domain.getOrders()
                        .stream()
                        .map(OrderMapper::toEntity)
                        .collect(Collectors.toList())
        );

        entity.setReviews(
                domain.getReviews()
                        .stream()
                        .map(ReviewMapper::toEntity)
                        .collect(Collectors.toList())
        );

        return entity;
    }
}