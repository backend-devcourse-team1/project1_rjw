package com.grepp.cafe.service;

import com.grepp.cafe.domain.Order;
import com.grepp.cafe.domain.OrderItem;
import com.grepp.cafe.domain.OrderStatus;
import com.grepp.cafe.domain.Product;
import com.grepp.cafe.dto.CreateOrderDto;
import com.grepp.cafe.dto.OrderItemDto;
import com.grepp.cafe.repository.OrderRepository;
import com.grepp.cafe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    //주문 => 주문자 등록 후 UUID 가져오고 => 상품 종류, 개수 묶음 돌려서 UUID 넣고 종류별 UUID 받아와서 id 넣어주고 카테고리 총가격 양 넣어주고
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    public void createOrder(CreateOrderDto orderDto) {

        Order order = new Order();
        order.setOrderId(UUID.randomUUID());
        order.setEmail(orderDto.getEmail());
        order.setAddress(orderDto.getAddress());
        order.setPostcode(orderDto.getPostcode());
        order.setOrderStatus(OrderStatus.ORDER);
        order.setCreatedAt(LocalDateTime.now());

        // 제품 이름 찾아서
        List<String> productNames = orderDto.getOrderItemDtos()
                .stream()
                .map(OrderItemDto::getProductName)
                .toList();

        // 제품 이름으로 데이터 가져와서 매칭
        Map<String, Product> productMap = productRepository.findByProductNameIn(productNames)
                .stream()
                .collect(Collectors.toMap(Product::getProductName, Function.identity()));

        // OrderItems 구하기
        List<OrderItem> orderItems = orderDto.getOrderItemDtos().stream()
                .map(itemDto -> {
                    Product product = productMap.get(itemDto.getProductName());
                    return OrderItem.createOrderItem(
                            order, product, product.getCategory(), product.getPrice(), itemDto.getQuantity());
                })
                .collect(Collectors.toList());

        // OrderItems 넣어주고
        order.setOrderItems(orderItems);

        // OrderItems 까지 넣은걸 저장
        orderRepository.save(order);
    }

    // 주문 수정
    @Transactional
    public Order updateOrderStatus(UUID orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(OrderStatus.valueOf(status));
        order.setUpdatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    // 주문 취소
    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepository.delete(order);
    }

    // 모든 주문 조회
    public List<Order> getAllOrders() {
        return orderRepository.findAllWithOrderItems();
    }

    @Transactional
    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmailWithOrderItems(email);
    }
}