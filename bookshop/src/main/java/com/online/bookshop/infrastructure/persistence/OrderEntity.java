package com.online.bookshop.infrastructure.persistence;

import com.online.bookshop.domain.model.enums.OrderStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ship_address", nullable = false)
    private String shipAddress;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 20, nullable = false)
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity() {}

    public OrderEntity(String shipAddress, LocalDate orderDate, OrderStatus orderStatus, UserEntity user) {
        this.shipAddress = shipAddress;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public UserEntity getUser() {
        return user;
    }

    public List<OrderItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(OrderItemEntity item) {
        items.add(item);
        item.setOrder(this);
    }

    public void removeItem(OrderItemEntity item) {
        items.remove(item);
        item.setOrder(null);
    }

    @Transient
    public double getTotalPrice() {
        return items.stream().mapToDouble(OrderItemEntity::getTotalPrice).sum();
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setItems(List<OrderItemEntity> items) {
        this.items = items;
    }
}
