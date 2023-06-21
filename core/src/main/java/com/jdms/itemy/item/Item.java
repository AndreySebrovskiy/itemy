package com.jdms.itemy.item;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

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

    @Column(name = "description")
    private String description;

    @Column(name = "year")
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate year;

    public Item(String name, String description, LocalDate year) {
        this.name = name;
        this.description = description;
        this.year = year;
    }

    public Item(Long id, String name, String description, LocalDate year) {
        this(name, description, year);
        this.id = id;

    }

}
