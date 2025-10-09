package com.online.bookshop.infrastructure.repository;

import com.online.bookshop.infrastructure.persistence.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaOrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUser_Id(Long userId);
}
