package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.domain.model.Order;
import com.online.bookshop.domain.repository.OrderRepository;
import com.online.bookshop.infrastructure.mapper.OrderMapper;
import com.online.bookshop.infrastructure.persistence.OrderEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final JpaOrderRepository jpaRepo;

    public OrderRepositoryImpl(JpaOrderRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<Order> findAll() {
        return jpaRepo.findAll().stream()
                .map(OrderMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaRepo.findById(id).map(OrderMapper::toDomain);
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        OrderEntity saved = jpaRepo.save(entity);
        return OrderMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public List<Order> findByUserId(Long userId) {
        return jpaRepo.findByUser_Id(userId).stream()
                .map(OrderMapper::toDomain)
                .toList();
    }
}
