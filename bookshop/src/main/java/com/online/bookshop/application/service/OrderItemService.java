package com.online.bookshop.application.service;

import com.online.bookshop.domain.model.Book;
import com.online.bookshop.domain.model.Order;
import com.online.bookshop.domain.model.OrderItem;
import com.online.bookshop.domain.repository.BookRepository;
import com.online.bookshop.domain.repository.OrderItemRepository;
import com.online.bookshop.domain.repository.OrderRepository;
import com.online.bookshop.infrastructure.mapper.BookMapper;
import com.online.bookshop.infrastructure.mapper.OrderItemMapper;
import com.online.bookshop.infrastructure.mapper.OrderMapper;
import com.online.bookshop.infrastructure.persistence.OrderItemEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository repository;
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public OrderItemService(OrderItemRepository repository,
                            OrderRepository orderRepository,
                            BookRepository bookRepository) {
        this.repository = repository;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public List<OrderItem> findAll() {
        return repository.findAll();
    }

    public Optional<OrderItem> findById(Long id) {
        return repository.findById(id);
    }

    public OrderItem save(OrderItem item) {
        OrderItemEntity entity = OrderItemMapper.toEntity(item);

        Optional<Order> order = orderRepository.findById(item.getOrderId());
        Optional<Book> book = bookRepository.findById(item.getBookId());

        entity.setOrder(OrderMapper.toEntity(order.orElse(null)));
        entity.setBook(BookMapper.toEntity(book.orElse(null)));

        return repository.save(OrderItemMapper.toDomain(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<OrderItem> findByOrderId(Long orderId) {
        return repository.findByOrderId(orderId);
    }
}
