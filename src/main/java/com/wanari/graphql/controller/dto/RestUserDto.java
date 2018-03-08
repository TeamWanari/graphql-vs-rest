package com.wanari.graphql.controller.dto;

import com.wanari.graphql.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class RestUserDto {
    public Long id;
    public String login;
    public List<RestRoleDto> roles;
    public RestPrinterDto printer;

    public static List<RestUserDto> from(List<User> users) {
        return users.stream().map(RestUserDto::from).collect(Collectors.toList());
    }

    public static RestUserDto from(User user) {
        RestUserDto dto = new RestUserDto();
        dto.id = user.id;
        dto.login = user.login;
        dto.roles = user.roles.stream().map(RestRoleDto::from).collect(Collectors.toList());
        dto.printer = RestPrinterDto.from(user.printer);
        return dto;
    }
}
