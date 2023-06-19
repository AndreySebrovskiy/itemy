package com.jdms.itemy.item.impl;

import com.jdms.itemy.item.Item;
import com.jdms.itemy.item.exception.ItemNotFoundException;
import com.jdms.itemy.item.model.BulkCreateItems;
import com.jdms.itemy.item.model.CreateItem;
import com.jdms.itemy.item.model.ItemMapper;
import com.jdms.itemy.item.model.UpdateItem;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;
    private final ItemMapper itemMapper;

    /**
     * Item by id, name and year
     *
     * @param id   id filter
     * @param name name filter
     * @param year name year

     * @return Item
     */
    public Item fetchByIdNameAndYear(Long id, String name, LocalDate year) {
        return repository.findByIdNameYear(id, name, year);
    }

    /**
     * Get items page
     * @param page required page number
     * @param size required page size
     * @return Pageable list of Items
     */
    public Page<Item> getItemsPage(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return repository.findAll(pageable);
    }


    /**
     * Create Item
     *
     * @param createItem
     * @return an updated Item
     */
    @Transactional
    public Item create(CreateItem createItem) {
        return repository.save(itemMapper.map(createItem));
    }

    /**
     * TODO better to use here TransactionTemplate with result mapping
     * @param createItems contains list of items
     */
    public void createItems(BulkCreateItems createItems) {
        repository.saveAll(itemMapper.map(createItems.getCreateItemList()));
    }

    /**
     * Update Item endpoint
     *
     * @param updateItem  updateItem
     * @param id          Item ID required to be updated
     * @return an updated Item
     */
    @Transactional
    public Item update(Long id, UpdateItem updateItem) {
        repository.update(updateItem.getName(), updateItem.getDescription(), id);
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    /**
     *
     * @param id Item Id
     * @return Item
     */
    public Item findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    /**
     * Delete item by Id
     * @param id Item by Id
     */
    public void deleteItem(Long id) {
        repository.deleteById(id);
    }


    private List<Sort.Order> createSortOrder(List<String> sortList, String sortDirection) {
        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;
        for (String sort : sortList) {
            if (sortDirection != null) {
                direction = Sort.Direction.fromString(sortDirection);
            } else {
                direction = Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }
        return sorts;
    }
}
