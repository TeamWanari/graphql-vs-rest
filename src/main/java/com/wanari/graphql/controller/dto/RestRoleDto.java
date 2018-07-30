package com.wanari.graphql.controller.dto;

import com.wanari.graphql.domain.Role;

import java.util.List;
import java.util.stream.Collectors;

public class RestRoleDto {
    public String key;
    public List<RestPrivilegeDto> privileges;

    public static RestRoleDto from(Role role) {
        RestRoleDto dto = new RestRoleDto();
        dto.key = role.keyName;
        dto.privileges = role.privileges.stream().map(RestPrivilegeDto::from).collect(Collectors.toList());
        return dto;
    }
}
