package com.grepp.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class OrderForm {
    private String email;
    private String address;
    private String postcode;

    public OrderForm() {
    }
}
