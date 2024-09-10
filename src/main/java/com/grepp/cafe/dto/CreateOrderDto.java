package com.grepp.cafe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrderDto {
    private String email;
    private String address;
    private String postcode;
    private List<OrderItemDto> orderItemDtos;

    public CreateOrderDto(String email, String address, String postcode, List<OrderItemDto> orderItems) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderItemDtos = orderItems;
    }
}
