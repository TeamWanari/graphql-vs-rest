package com.wanari.graphql.selective_rest.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wanari.graphql.domain.Privilege;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectivePrivilegeDto {
    public String key;

    public static SelectivePrivilegeDto from(Privilege privilege) {
        SelectivePrivilegeDto dto = new SelectivePrivilegeDto();
        dto.key = privilege.key;
        return dto;
    }
}
