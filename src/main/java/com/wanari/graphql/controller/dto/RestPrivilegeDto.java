package com.wanari.graphql.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wanari.graphql.domain.Privilege;

@JsonInclude(Include.NON_NULL)
public class RestPrivilegeDto {
    public String key;

    public static RestPrivilegeDto from(Privilege privilege) {
        RestPrivilegeDto dto = new RestPrivilegeDto();
        dto.key = privilege.keyName;
        return dto;
    }
}
