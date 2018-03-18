package com.wanari.graphql.selective_rest.controller.dto;

import com.wanari.graphql.domain.Privilege;

public class SelectivePrivilegeDto {
    public String key;

    public static SelectivePrivilegeDto from(Privilege privilege) {
        SelectivePrivilegeDto dto = new SelectivePrivilegeDto();
        dto.key = privilege.key;
        return dto;
    }
}
