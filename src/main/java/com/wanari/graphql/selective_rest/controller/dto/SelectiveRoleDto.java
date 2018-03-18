package com.wanari.graphql.selective_rest.controller.dto;

import com.wanari.graphql.domain.Role;

import java.util.List;
import java.util.stream.Collectors;

public class SelectiveRoleDto {
    public String key;
    public List<SelectivePrivilegeDto> privileges;

    public static SelectiveRoleDto from(Role role) {
        SelectiveRoleDto dto = new SelectiveRoleDto();
        dto.key = role.key;
        dto.privileges = role.privileges.stream().map(SelectivePrivilegeDto::from).collect(Collectors.toList());
        return dto;
    }
}