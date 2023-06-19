package com.jdms.itemy.item.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateItem {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
