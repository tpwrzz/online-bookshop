package com.online.bookshop.infrastructure.persistence;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name="book_id", nullable=false)
    private BookEntity book;

    @Column(name="quantity", nullable=false)
    private int quantity;

    @Column(name="unit_price", nullable=false)
    private double unitPrice;

    public OrderItemEntity() {
    }

    public OrderItemEntity(OrderEntity order , BookEntity book , int quantity) {
        setOrder(order);
        setBook(book);
        setQuantity(quantity);
    }

    public Long getId() {
        return id;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
        if (book != null) {
            this.unitPrice = book.getPrice();
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    @Transient
    public double getTotalPrice() {
        return quantity * unitPrice;
    }

}
