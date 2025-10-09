package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Order;
import com.online.bookshop.domain.model.User;
import com.online.bookshop.domain.repository.OrderRepository;
import com.online.bookshop.domain.repository.UserRepository;
import com.online.bookshop.infrastructure.mapper.OrderMapper;
import com.online.bookshop.infrastructure.mapper.UserMapper;
import com.online.bookshop.infrastructure.persistence.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }

    public Order save(Order order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        Optional<User> user = userRepository.findById(order.getUserId());
        entity.setUser(UserMapper.toEntity(user.orElse(null)));
        return repository.save(OrderMapper.toDomain(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<Order> findByUserId(Long userId) {
        return repository.findByUserId(userId);
    }
}
