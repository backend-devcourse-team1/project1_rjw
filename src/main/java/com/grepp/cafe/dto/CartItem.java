package com.grepp.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
public class CartItem {
    private UUID productId;
    private String productName;
    private int quantity;
    private long price;

    public CartItem() {
    }
}
