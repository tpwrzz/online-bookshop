package com.online.bookshop.domain.model;

import java.time.LocalDate;

public class Order {
    private Long id;
    private Long cartId;
    private int quantity;
    private String shipAddress;
    private LocalDate orderDate;
    private Long orderStatusId;
    private double totalPrice;

    public Order(Long id, Long cartId, int quantity, String shipAddress,
                 LocalDate orderDate, Long orderStatusId, double totalPrice) {
        this.id = id;
        this.cartId = cartId;
        this.quantity = quantity;
        this.shipAddress = shipAddress;
        this.orderDate = orderDate;
        this.orderStatusId = orderStatusId;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
