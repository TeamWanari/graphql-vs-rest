package com.wanari.graphql.controller;

import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
        value = "",
        method = RequestMethod.GET)
    public List<RestUserDto> getAllUser() {
        return userService.findAllForRest().stream().map(RestUserDto::from).collect(Collectors.toList());
    }

    @RequestMapping(
            value = "rest_test",
            method = RequestMethod.GET)
    public List<RestUserDto> getUsersWithSelectedFields(@RequestParam List<String> fields, @RequestParam(required=false) String sortBy, @RequestParam(required=false) String sortOrder, @RequestParam Map<String,String> allRequestParams) {
        return userService.findUsersWithSelectedFields(fields, sortBy, sortOrder, allRequestParams);
    }
}
