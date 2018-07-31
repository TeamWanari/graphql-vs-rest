package com.wanari.graphql.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wanari.graphql.domain.User;

@JsonInclude(Include.NON_NULL)
public class RestPrinterOwnerDto {
    public long id;

    public static RestPrinterOwnerDto from(User owner) {
        RestPrinterOwnerDto dto = new RestPrinterOwnerDto();
        dto.id = owner.id;
        return dto;
    }
}
