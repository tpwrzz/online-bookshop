package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Order;
import com.online.bookshop.domain.model.OrderItem;
import com.online.bookshop.domain.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Order save(Order order) {
        Order saved = repository.save(order);
        List<OrderItem> new_items = new ArrayList<>();
        for (OrderItem item : saved.getItems()) {
            item.setOrderId(saved.getId());
            new_items.add(item);
        }
        saved.setItems(new_items);
        return saved;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Order> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}
