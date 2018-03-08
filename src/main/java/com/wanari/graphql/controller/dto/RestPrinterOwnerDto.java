package com.wanari.graphql.controller.dto;

import com.wanari.graphql.domain.User;

public class RestPrinterOwnerDto {
    public long id;

    public static RestPrinterOwnerDto from(User owner) {
        RestPrinterOwnerDto dto = new RestPrinterOwnerDto();
        dto.id = owner.id;
        return dto;
    }
}
