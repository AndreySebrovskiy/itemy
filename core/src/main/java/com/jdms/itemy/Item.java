package com.jdms.itemy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = Item.ENTITY)
@Data
@RequiredArgsConstructor
public class Item {
    public static final String ENTITY = "item";
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "currency")
    private String currency;

    public Item(String name, BigDecimal price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }
}
