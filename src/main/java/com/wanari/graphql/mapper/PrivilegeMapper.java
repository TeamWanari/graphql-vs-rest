package com.wanari.graphql.mapper;

import com.wanari.graphql.controller.dto.RestPrivilegeDto;
import com.wanari.graphql.domain.Privilege;
import com.wanari.generic_filter.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrivilegeMapper implements GenericMapper<RestPrivilegeDto, Privilege> {

    public RestPrivilegeDto mapWithFields(Privilege privilege, List<String> fields) {
        RestPrivilegeDto dto = new RestPrivilegeDto();
        dto.key = privilege.keyName;
        return dto;
    }
}
