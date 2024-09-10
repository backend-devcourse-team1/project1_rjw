package com.grepp.cafe.service;

import com.grepp.cafe.domain.Order;
import com.grepp.cafe.domain.OrderItem;
import com.grepp.cafe.dto.CreateOrderDto;
import com.grepp.cafe.dto.OrderItemDto;
import com.grepp.cafe.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void createOrder() {
        // given
        List<OrderItemDto> orderItemDtos = Arrays.asList(
                new OrderItemDto("Coffee Bean1", 2),
                new OrderItemDto("Coffee Bean2", 1),
                new OrderItemDto("Coffee Bean3", 3)
        );
        CreateOrderDto createOrderDto = new CreateOrderDto(
                "test@test.com",
                "123 Test Street",
                "12345",
                orderItemDtos
        );

        // when
        orderService.createOrder(createOrderDto);

        // then
        List<Order> orders = orderRepository.findByEmailWithOrderItems("test@test.com");
        for (Order order : orders) {
            System.out.println(order);
        }

    }

    @Test
    void updateOrderStatus() {
    }

    @Test
    void cancelOrder() {
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void getOrderItemsByEmail() {
        List<Order> allOrders = orderService.getOrdersByEmail("test@test.com");
        for (Order allOrder : allOrders) {
            List<OrderItem> orderItems = allOrder.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                System.out.println("orderItem = " + orderItem);
            }
        }
    }

    @Test
    void getOrdersByEmail() {
    }


}