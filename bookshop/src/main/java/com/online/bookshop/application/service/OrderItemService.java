package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.OrderItem;
import com.online.bookshop.domain.repository.OrderItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public List<OrderItem> findAll() {
        return repository.findAll();
    }

    public Optional<OrderItem> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public OrderItem save(OrderItem item) {
        return repository.save(item);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<OrderItem> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}
