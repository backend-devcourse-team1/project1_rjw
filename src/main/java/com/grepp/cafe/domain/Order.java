package com.grepp.cafe.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "orders")
@ToString(exclude = "orderItems")
public class Order {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID orderId;

    @NotNull
    @Email // 이메일 형식 유효성 검사
    @Size(max = 50) // 최대 길이 50
    @Column(nullable = false)
    private String email;

    @NotNull
    @Size(max = 200) // 최대 길이 200
    @Column(nullable = false)
    private String address;

    @NotNull
    @Size(max = 200) // 최대 길이 200
    @Column(nullable = false)
    private String postcode;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Getter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

}
