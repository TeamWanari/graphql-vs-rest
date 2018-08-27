package com.wanari.graphql.mapper;

import com.wanari.graphql.controller.dto.RestRoleDto;
import com.wanari.graphql.domain.Role;
import com.wanari.graphql.domain.constants.PrivilegeConstants;
import com.wanari.graphql.domain.constants.RoleConstants;
import com.wanari.utils.spring.genericfilter.GenericFilterUtil;
import com.wanari.utils.spring.genericfilter.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper implements GenericMapper<RestRoleDto, Role> {

    private final PrivilegeMapper privilegeMapper;

    public RoleMapper(PrivilegeMapper privilegeMapper) {
        this.privilegeMapper = privilegeMapper;
    }

    public RestRoleDto mapWithFields(Role role, List<String> fields) {
        RestRoleDto dto = new RestRoleDto();
        dto.key = role.keyName;
        if(fields.contains(RoleConstants.FieldName.PRIVILEGES)) {
            List<String> privilegeFields = GenericFilterUtil.getNestedFields(fields, PrivilegeConstants.ENTITY_NAME);
            dto.privileges = privilegeMapper.mapWithFields(role.privileges, privilegeFields, Collectors.toList());
        }
        return dto;
    }
}
