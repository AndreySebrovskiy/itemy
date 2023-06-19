package com.jdms.itemy.item.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BulkCreateItems {
    @NotNull
    @Size(min = 1)
    List<CreateItem> createItemList;
}
