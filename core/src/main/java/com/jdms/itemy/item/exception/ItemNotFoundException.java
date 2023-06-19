package com.jdms.itemy.item.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long id) {
        super(String.format("No item entity with id %s exists!", id));
    }
}
