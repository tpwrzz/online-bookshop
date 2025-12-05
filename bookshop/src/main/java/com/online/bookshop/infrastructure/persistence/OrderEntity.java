package com.online.bookshop.infrastructure.persistence;

import com.online.bookshop.domain.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Getter
    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "ship_address", nullable = false)
    private String shipAddress;

    @Setter
    @Getter
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Setter
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 20, nullable = false)
    private OrderStatus orderStatus;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Setter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItemEntity> items = new ArrayList<>();

    public OrderEntity() {}

    public List<OrderItemEntity> getItems() {
        return Collections.unmodifiableList(items);
    }
}
