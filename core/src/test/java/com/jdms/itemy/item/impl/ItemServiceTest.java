    package com.jdms.itemy.item.impl;

import com.jdms.itemy.item.Item;
import com.jdms.itemy.item.model.ItemCoreMapper;
import com.jdms.itemy.item.model.UpdateItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@Import(ItemService.class)
public class ItemServiceTest {

    @Autowired
    private ItemService service;

    @MockBean
    private ItemRepository repository;
    @MockBean
    private ItemCoreMapper itemMapper;

    @Test
    public void testUpdate() {
        Item item = getDefaultItem(0);
        given(repository.findById(any())).willReturn(Optional.of(item));

        Item actual = service.update(Long.valueOf(0), UpdateItem.builder().name("new").description("new").build());
        assertThat(actual).isEqualTo(item);
    }

    @Test
    public void test() {
        given(repository.findByIdNameYear(any(), any(), any())).willReturn(getDefaultItem(1));
        Item actual = service.fetchByIdNameAndYear(Long.valueOf(1), "name-1",  LocalDate.of(2023,6,6));
        assertThat(actual).isEqualTo(getDefaultItem(1));
    }

    private Item getDefaultItem(long id) {
        Item result = new Item("testName-" + id, "desc-" + id, LocalDate.of(2023,6,6));
        result.setId(id);
        return result;
    }

    private Page<Item> getDefaultPageable() {
        List<Item> result = new ArrayList<>();
        for (long i = 0; i < 20; i++) {
            result.add(getDefaultItem(i));
        }
        return new PageImpl<>(result);
    }
}
