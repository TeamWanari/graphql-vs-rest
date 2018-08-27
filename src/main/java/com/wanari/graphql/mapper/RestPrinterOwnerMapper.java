package com.wanari.graphql.mapper;

import com.wanari.graphql.controller.dto.RestPrinterOwnerDto;
import com.wanari.graphql.domain.User;
import com.wanari.utils.spring.genericfilter.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestPrinterOwnerMapper implements GenericMapper<RestPrinterOwnerDto, User> {
    @Override
    public RestPrinterOwnerDto mapWithFields(User owner, List<String> fields) {
        RestPrinterOwnerDto dto = new RestPrinterOwnerDto();
        dto.id = owner.id;
        return dto;
    }
}
