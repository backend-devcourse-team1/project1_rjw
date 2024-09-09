package com.grepp.cafe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "order_items")
@ToString
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long seq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Long price;

    @NotNull
    @Min(1) // 최소 1개의 수량이 필요함
    @Column(nullable = false)
    private int quantity;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static OrderItem createOrderItem(Order order, Product product, Category category, Long price, int quantity) {
        OrderItem orderItem = new OrderItem();
        orderItem.order = order;
        orderItem.product = product;
        orderItem.category = category;
        orderItem.price = price;
        orderItem.quantity = quantity;
        orderItem.createdAt = LocalDateTime.now();

        return orderItem;
    }
}
