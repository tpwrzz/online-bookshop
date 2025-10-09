package com.online.bookshop.domain.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Long id;
    private String shipAddress;
    private LocalDate orderDate;
    private Long orderStatusId;
    private Long userId;
    private List<OrderItem> items;

    public Order(Long id, String shipAddress, LocalDate orderDate, Long orderStatusId, Long userId, List<OrderItem> items) {
        this.id = id;
        this.shipAddress = shipAddress;
        this.orderDate = orderDate;
        this.orderStatusId = orderStatusId;
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Long getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Long orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
