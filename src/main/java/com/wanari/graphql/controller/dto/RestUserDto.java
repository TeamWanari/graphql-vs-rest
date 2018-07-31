package com.wanari.graphql.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wanari.graphql.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(Include.NON_NULL)
public class RestUserDto {
    public Long id;
    public String login;
    public List<RestRoleDto> roles;
    public List<RestPrinterDto> printers;

    public static List<RestUserDto> from(List<User> users) {
        return users.stream().map(RestUserDto::from).collect(Collectors.toList());
    }

    public static RestUserDto from(User user) {
        RestUserDto dto = new RestUserDto();
        dto.id = user.id;
        dto.login = user.login;
        dto.roles = user.roles.stream().map(RestRoleDto::from).collect(Collectors.toList());
        dto.printers = user.printers.stream().map(RestPrinterDto::from).collect(Collectors.toList());
        return dto;
    }
}
