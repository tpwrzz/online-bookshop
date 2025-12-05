package com.online.bookshop.api.controller;

import com.online.bookshop.application.service.OrderItemService;
import com.online.bookshop.domain.model.OrderItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderItem> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderItem> create(@RequestBody OrderItem orderItem) {
        return ResponseEntity.ok(service.save(orderItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItem> getByOrderId(@PathVariable Long orderId) {
        return service.findByOrderId(orderId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItem orderItem) {
        return service.findById(id)
                .map(existing -> {
                    orderItem.setId(id);
                    OrderItem saved = service.save(orderItem);
                    return ResponseEntity.ok(saved);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
