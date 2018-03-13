package com.wanari.graphql.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wanari.graphql.domain.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestUserDto {
    public Long id;
    public String login;
    public List<RestRoleDto> roles;
//    public RestPrinterDto printer;

    public static List<RestUserDto> from(List<User> users) {
        return users.stream().map(RestUserDto::from).collect(Collectors.toList());
    }

    public static RestUserDto from(User user) {
        RestUserDto dto = new RestUserDto();
        dto.id = user.id;
        dto.login = user.login;
        dto.roles = user.roles.stream().map(RestRoleDto::from).collect(Collectors.toList());
//        dto.printer = RestPrinterDto.from(user.printer); //TODO
        return dto;
    }

    public static RestUserDto withSelectedFields(User user, List<String> fields, Map<String,String> allRequestParams){
        RestUserDto dto = new RestUserDto();
        if(fields.contains("id")){
            dto.id = user.id;
        }
        if(fields.contains("login")){
            dto.login = user.login;
        }
        if(fields.contains("roles")){
            dto.roles = user.roles.stream().map(RestRoleDto::from).collect(Collectors.toList());
        }
        return dto;
    }
}
