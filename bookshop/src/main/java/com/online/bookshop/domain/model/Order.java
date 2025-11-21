package com.online.bookshop.domain.model;

import com.online.bookshop.domain.model.enums.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class Order {
    private Long id;
    private String shipAddress;
    private LocalDate orderDate;
    private OrderStatus orderStatus;
    private Long userId;
    private List<OrderItem> items;

    public Order(Long id, String shipAddress, LocalDate orderDate, OrderStatus orderStatus, Long userId, List<OrderItem> items) {
        this.id = id;
        this.shipAddress = shipAddress;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.userId = userId;
        this.items = items;
    }

}
