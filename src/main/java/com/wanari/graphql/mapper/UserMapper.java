package com.wanari.graphql.mapper;

import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.domain.User;
import com.wanari.generic_filter.GenericFilterUtil;
import com.wanari.generic_filter.GenericMapper;
import com.wanari.graphql.domain.constants.PrinterConstants;
import com.wanari.graphql.domain.constants.RoleConstants;
import com.wanari.graphql.domain.constants.UserConstants;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper implements GenericMapper<RestUserDto, User> {

    private final RoleMapper roleMapper;
    private final PrinterMapper printerMapper;

    public UserMapper(RoleMapper roleMapper, PrinterMapper printerMapper) {
        this.roleMapper = roleMapper;
        this.printerMapper = printerMapper;
    }


    public RestUserDto mapWithFields(User user, List<String> fields) {
        RestUserDto dto = new RestUserDto();
        dto.id = user.id;
        if(fields.contains(UserConstants.FieldName.LOGIN)) {
            dto.login = user.login;
        }
        if(fields.contains(UserConstants.FieldName.ROLES)) {
            List<String> roleFields = GenericFilterUtil.getNestedFields(fields, RoleConstants.ENTITY_NAME);
            dto.roles = roleMapper.mapWithFields(user.roles, roleFields, Collectors.toList());
        }
        if(fields.contains(UserConstants.FieldName.PRINTERS)) {
            List<String> printerFields = GenericFilterUtil.getNestedFields(fields, PrinterConstants.ENTITY_NAME);
            dto.printers = printerMapper.mapWithFields(user.printers, printerFields, Collectors.toList());
        }
        return dto;
    }
}
