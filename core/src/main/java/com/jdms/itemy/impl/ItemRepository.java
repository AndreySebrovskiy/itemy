package com.jdms.itemy.impl;

import com.jdms.itemy.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface ItemRepository extends JpaRepository<Item, Long> {
    String FILTER_ITEMS_ON_NAME_QUERY = "select b from Item b where UPPER(b.name) like CONCAT('%',UPPER(?1),'%')";
    String UPDATE_QUERY = "UPDATE Item b SET b.name = ?1, b.price = ?2, b.price =?3 WHERE b.id = ?4";

    @Query(FILTER_ITEMS_ON_NAME_QUERY)
    Page<Item> findByNameLike(String nameFilter, Pageable pageable);

    @Query(UPDATE_QUERY)
    @Modifying
    void update(String name, BigDecimal price, String currency, Long id);
}
