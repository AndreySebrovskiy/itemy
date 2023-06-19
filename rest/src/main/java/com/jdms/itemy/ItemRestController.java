package com.jdms.itemy;

import com.jdms.itemy.impl.ItemService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@OpenAPIDefinition(tags = {
        @Tag(name = "Items", description = "REST API for items operations")
})
@RestController
@RequestMapping(value = "/api/items", produces= MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class ItemRestController {

    private final ItemService service;

}
