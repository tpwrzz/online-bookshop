package com.online.bookshop.domain.repository;

import com.online.bookshop.domain.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository {
    List<OrderItem> findAll();

    List<OrderItem> findByOrderId(Long orderId);

    Optional<OrderItem> findById(Long id);

    OrderItem save(OrderItem orderItem);

    void deleteById(Long id);
}
