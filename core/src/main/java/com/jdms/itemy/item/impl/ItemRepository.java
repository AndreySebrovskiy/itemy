package com.jdms.itemy.item.impl;


import com.jdms.itemy.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ItemRepository extends JpaRepository<Item, Long> {
    String FILTER_ITEMS_ON_ID_NAME_YEAR_QUERY = "select b from Item b where b.id = ?1 and UPPER(b.name) like CONCAT('%',UPPER(?2),'%') and b.year = ?3";
    String UPDATE_QUERY = "UPDATE Item b SET b.name = ?1, b.description = ?2, b.year = ?3 WHERE b.id = ?4";

    @Query(FILTER_ITEMS_ON_ID_NAME_YEAR_QUERY)
    Item findByIdNameYear(Long id, String name, LocalDate year);

    @Query(UPDATE_QUERY)
    @Modifying
    void update(String name, String description, LocalDate year, Long id);

}
