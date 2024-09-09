package com.grepp.cafe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderItemDto {
    private String productName;
    private int quantity;

    public OrderItemDto(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }
}
