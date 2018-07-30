package com.wanari.graphql.controller.dto;

import com.wanari.graphql.domain.Privilege;

public class RestPrivilegeDto {
    public String key;

    public static RestPrivilegeDto from(Privilege privilege) {
        RestPrivilegeDto dto = new RestPrivilegeDto();
        dto.key = privilege.keyName;
        return dto;
    }
}
