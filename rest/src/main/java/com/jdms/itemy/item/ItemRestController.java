package com.jdms.itemy.item;

import com.jdms.itemy.item.impl.ItemService;
import com.jdms.itemy.item.model.*;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.jdms.itemy.item.ItemRestController.ITEMS_URI;

@OpenAPIDefinition(tags = {
        @Tag(name = "Items", description = "REST API for items operations")
})
@RestController
@RequestMapping(value = ITEMS_URI, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class ItemRestController {
    public static final String ITEMS_URI = "/api/items";

    private final ItemService service;
    private final ItemMapper itemMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemResponse createItem(@RequestBody CreateItem request) {
        return itemMapper.map(service.create(request));
    }

    @GetMapping("/item/{id}")
    public ItemResponse getItems(@PathVariable Long id,
                                 @RequestParam(value = "name") String name,
                                 @RequestParam(value = "year") LocalDate year) {
        return itemMapper.map(service.fetchByIdNameAndYear(id, name, year));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ItemResponse updateItem(@PathVariable Long id, @RequestBody UpdateItem updateItem) {
      return itemMapper.map(service.update(id, updateItem));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
    }

    @GetMapping("/pageable")
    @ResponseStatus(HttpStatus.FOUND)
    public Page<ItemResponse> findItems(int page, int size) {
        return itemMapper.mapToResponse(service.getItemsPage(page, size));
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItems(@RequestBody BulkCreateItems items) {
        service.createItems(items);
    }
}
