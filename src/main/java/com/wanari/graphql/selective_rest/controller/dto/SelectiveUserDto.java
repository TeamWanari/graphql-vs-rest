package com.wanari.graphql.selective_rest.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wanari.graphql.domain.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectiveUserDto {
    public Long id;
    public String login;
    public List<SelectiveRoleDto> roles;
//    public RestPrinterDto printer;

    public static List<SelectiveUserDto> from(List<User> users) {
        return users.stream().map(SelectiveUserDto::from).collect(Collectors.toList());
    }

    public static SelectiveUserDto from(User user) {
        SelectiveUserDto dto = new SelectiveUserDto();
        dto.id = user.id;
        dto.login = user.login;
        dto.roles = user.roles.stream().map(SelectiveRoleDto::from).collect(Collectors.toList());
//        dto.printer = RestPrinterDto.from(user.printer); //TODO
        return dto;
    }

    public static SelectiveUserDto withSelectedFields(User user, List<String> fields){
        SelectiveUserDto dto = new SelectiveUserDto();
        if(fields.contains("id")){
            dto.id = user.id;
        }
        if(fields.contains("login")){
            dto.login = user.login;
        }
        if(fields.contains("roles")){
            dto.roles = user.roles.stream().map(SelectiveRoleDto::from).collect(Collectors.toList());
        }
        return dto;
    }
}
