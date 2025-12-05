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
                entity.getOrder().getId(),
                entity.getBook().getId(),
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }

    public static OrderItemEntity toEntity(OrderItem domain) {
        if (domain == null) return null;

        OrderItemEntity entity = new OrderItemEntity();
        if (domain.getId() != 0) entity.setId(domain.getId());
        entity.setOrder(OrderMapper.refOrder(domain.getOrderId()));
        entity.setBook(BookMapper.refBook(domain.getBookId()));
        entity.setQuantity(domain.getQuantity());
        entity.setUnitPrice(domain.getUnitPrice());
        return entity;
    }
}