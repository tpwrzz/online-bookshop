package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.domain.model.OrderItem;
import com.online.bookshop.domain.repository.OrderItemRepository;
import com.online.bookshop.infrastructure.mapper.OrderItemMapper;
import com.online.bookshop.infrastructure.persistence.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepository {

    private final JpaOrderItemRepository jpaRepository;

    @Override
    public List<OrderItem> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItem> findByOrderId(Long orderId) {
        return jpaRepository.findByOrderId(orderId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderItem> findById(Long id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        var saved = jpaRepository.save(OrderItemMapper.toEntity(orderItem));
        return toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    private OrderItem toDomain(OrderItemEntity entity) {
        return new OrderItem(
                entity.getId(),
                entity.getOrder().getId(),
                entity.getBook().getId(),
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }
}
