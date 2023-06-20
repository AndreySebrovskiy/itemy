package com.jdms.itemy.item.model;


import com.jdms.itemy.item.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemCoreMapper {

    Item map(CreateItem item);

    List<Item> map(List<CreateItem> items);
}
