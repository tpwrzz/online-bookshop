package com.online.bookshop.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name="order_items")
public class OrderItemEntity {

    @Id
    @Setter
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id", nullable=false)
    private BookEntity book;

    @Column(name="quantity", nullable=false)
    private int quantity;

    @Setter
    @Column(name="unit_price", nullable=false)
    private double unitPrice;

    public OrderItemEntity() {
    }

    public void setBook(BookEntity book) {
        this.book = book;
        if (book != null) {
            this.unitPrice = book.getPrice();
        }
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
    }
}
