package com.grepp.cafe.repository;

import com.grepp.cafe.domain.Order;
import com.grepp.cafe.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.email = :email")
    List<Order> findByEmailWithOrderItems(@Param("email") String email);

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderItems")
    List<Order> findAllWithOrderItems();
}
