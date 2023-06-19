package com.jdms.itemy.item.model;

import com.jdms.itemy.item.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemResponse map(Item entity);

    List<ItemResponse> map(List<Item> items);

    default Page<ItemResponse> mapToResponse(Page<Item> cities) {
        return cities.map(this::map);
    }
}
