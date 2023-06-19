package com.jdms.itemy.impl;

import com.jdms.itemy.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository repository;

    /**
     * Pageable Item list with built-in searching & sorting
     * @param nameFilter  name filter
     * @param page        required page number
     * @param size        required page size
     * @param sortList    List of properties for sorting
     * @param sortOrder   sorting order
     * @return Pageable list of Items which are eligible to search criteria
     */
    public Page<Item> fetchDataAsPageWithFilteringAndSorting(String nameFilter, int page, int size, List<String> sortList, String sortOrder) {
        // create Pageable object using the page, size and sort details
        Pageable pageable = PageRequest.of(page, size, Sort.by(createSortOrder(sortList, sortOrder)));
        // fetch the page object by additionally passing pageable with the filters
        return repository.findByNameLike(nameFilter, pageable);
    }

    /**
     * Update Item endpoint
     * @param name   new Item name
     * @param price  new Item price
     * @param currency  new Item price currency
     * @param id     Item ID required to be updated
     * @return an updated Item
     */
    @Transactional
    public Item update(String name, BigDecimal price, String currency, Long id) {
        repository.update(name, price, currency, id);
        return repository.findById(id).orElse(null);
    }

    Item findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    /**
     * @return List of all Items.
     */
    public List<Item> findAll() {
        return repository.findAll();
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
