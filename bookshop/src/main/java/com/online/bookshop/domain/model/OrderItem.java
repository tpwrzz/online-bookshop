package com.online.bookshop.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderItem {
    private Long id;
    private Long orderId;
    private Long bookId;
    private int quantity;
    private double unitPrice;

    public OrderItem(Long id, Long orderId, Long bookId, int quantity, double unitPrice) {
        this.id = id;
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
