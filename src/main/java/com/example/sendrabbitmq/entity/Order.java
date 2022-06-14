package com.example.sendrabbitmq.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private String product;
    private int quantity;
    private int unitPrice;
    private int totalPrice;
}
