package com.grepp.cafe.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name = "products")
@ToString
public class Product {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID productId;

    @NotNull
    @Size(max = 20) // 최대 길이 20
    @Column(nullable = false)
    private String productName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @NotNull
    @Min(0) // 최소 값 0
    @Column(nullable = false)
    private Long price;

    @Size(max = 500) // 최대 길이 500
    private String description;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    public Product() {

    }

    public Product(UUID productId, String productName, Category category, Long price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }
}
