package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.Order;
import com.online.bookshop.infrastructure.persistence.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public static Order toDomain(OrderEntity entity) {
        if (entity == null) return null;

        return new Order(
                entity.getId(),
                entity.getShipAddress(),
                entity.getOrderDate(),
                entity.getOrderStatus(), // map enum to id
                entity.getUser() != null ? entity.getUser().getId() : null,
                entity.getItems()
                        .stream()
                        .map(OrderItemMapper::toDomain)
                        .collect(Collectors.toList())
        );
    }

    public static OrderEntity toEntity(Order domain) {
        if (domain == null) return null;

        OrderEntity entity = new OrderEntity();
        entity.setShipAddress(domain.getShipAddress());
        entity.setOrderDate(domain.getOrderDate());
        entity.setOrderStatus(domain.getOrderStatus());
        entity.setItems(
                domain.getItems()
                        .stream()
                        .map(OrderItemMapper::toEntity)
                        .collect(Collectors.toList())
        );

        return entity;
    }
}
