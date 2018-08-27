package com.wanari.graphql.controller;

import com.wanari.utils.spring.genericfilter.error.ErrorDto;
import org.springframework.http.ResponseEntity;

public class BaseController {
    protected <T> ResponseEntity<T> toResponse(T body) {
        return ResponseEntity
            .ok(body);
    }

    protected <T extends ErrorDto> ResponseEntity<T> errorToResponse(T error) {
        return ResponseEntity
            .status(error.status)
            .body(error);
    }
}
