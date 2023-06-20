package com.jdms.itemy.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UpdateItem {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private LocalDate year;
}
