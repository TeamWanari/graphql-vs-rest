package com.wanari.graphql.selective_rest.controller;

import com.wanari.graphql.selective_rest.SelectiveUserService;
import com.wanari.graphql.selective_rest.controller.dto.SelectiveUserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/selective")
public class SelectiveUserController {
    private final SelectiveUserService selectiveUserService;

    public SelectiveUserController(SelectiveUserService selectiveUserService) {
        this.selectiveUserService = selectiveUserService;
    }

    @RequestMapping(
            value = "",
            method = RequestMethod.GET)
    public List<SelectiveUserDto> getAllUser() {
        return selectiveUserService.findAllForRest().stream().map(SelectiveUserDto::from).collect(Collectors.toList());
    }

    @RequestMapping(
            value = "all",
            method = RequestMethod.GET)
    public List<SelectiveUserDto> getAll(@RequestParam List<String> fields) {
        return selectiveUserService.findAll(fields);
    }

    @RequestMapping(
            value = "hack",
            method = RequestMethod.GET)
    public List<SelectiveUserDto> getAllOnlyThisTimeHacky(@RequestParam List<String> fields) {
        return selectiveUserService.findAllHacky(fields);
    }

    @RequestMapping(
            value = "rest_test",
            method = RequestMethod.GET)
    public List<SelectiveUserDto> getUsersWithSelectedFields(@RequestParam List<String> fields, @RequestParam(required=false) String sortBy, @RequestParam(required=false) String sortOrder, @RequestParam Map<String,String> allRequestParams) {
        return selectiveUserService.findUsersWithSelectedFields(fields, sortBy, sortOrder, allRequestParams);
    }
}
