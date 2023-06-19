package com.jdms.itemy.item.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate year;
}
