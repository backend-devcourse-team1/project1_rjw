package com.grepp.cafe.repository;

import com.grepp.cafe.domain.Order;
import com.grepp.cafe.domain.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
//org.springframework.dao.InvalidDataAccessApiUsageException: No enum constant: enum 타입에 있는 값이 아닌 값이 db에 저장되어 있으면 오류남
class OrderRepositoryTest {

    @Autowired
    private OrderRepository repository;

    @Test
    void save() {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setEmail("test@test.com");
        order.setAddress("test2");
        order.setPostcode("test2");
        order.setOrderStatus(OrderStatus.ORDER);
        order.setCreatedAt(LocalDateTime.now());

        repository.save(order);
    }

    @Test
    void findByEmailWithOrderItems() {
    }

    @Test
    void findAllWithOrderItems() {
    }
}