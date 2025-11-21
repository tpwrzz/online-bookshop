package com.online.bookshop.infrastructure.mapper;

import com.online.bookshop.domain.model.OrderItem;
import com.online.bookshop.infrastructure.persistence.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public static OrderItem toDomain(OrderItemEntity entity) {
        if (entity == null) return null;

        return new OrderItem(
                entity.getId(),
                entity.getOrder() != null ? entity.getOrder().getId() : null,
                entity.getBook() != null ? entity.getBook().getId() : null,
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }

    public static OrderItemEntity toEntity(OrderItem domain) {
        if (domain == null) return null;

        OrderItemEntity entity = new OrderItemEntity();
        entity.setQuantity(domain.getQuantity());
        entity.setUnitPrice(domain.getUnitPrice());
        return entity;
    }
}