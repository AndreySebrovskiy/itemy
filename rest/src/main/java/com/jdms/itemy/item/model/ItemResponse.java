package com.jdms.itemy.item.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate year;
}
