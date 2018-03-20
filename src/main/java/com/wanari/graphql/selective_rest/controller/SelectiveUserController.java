package com.wanari.graphql.selective_rest.controller;

import com.wanari.graphql.selective_rest.controller.dto.SelectiveRoleDto;
import com.wanari.graphql.selective_rest.controller.dto.SelectiveUserDto;
import com.wanari.graphql.selective_rest.service.CriteriaUserService;
import com.wanari.graphql.selective_rest.service.SelectiveRoleService;
import com.wanari.graphql.selective_rest.service.SelectiveUserService;
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
    private final SelectiveRoleService selectiveRoleService;
    private final CriteriaUserService criteriaUserService;

    public SelectiveUserController(SelectiveUserService selectiveUserService, SelectiveRoleService selectiveRoleService, CriteriaUserService criteriaUserService) {
        this.selectiveUserService = selectiveUserService;
        this.selectiveRoleService = selectiveRoleService;
        this.criteriaUserService = criteriaUserService;
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
            value = "querydsl",
            method = RequestMethod.GET)
    public List<SelectiveUserDto> getUsersWithSelectedFieldsWithQueryDsl(@RequestParam List<String> fields, @RequestParam(required=false) String sortBy, @RequestParam(required=false) String sortOrder, @RequestParam Map<String,String> allRequestParams) {
        return selectiveUserService.findUsersWithSelectedFields(fields, sortBy, sortOrder, allRequestParams);
    }

    @RequestMapping(
            value = "separate_roles",
            method = RequestMethod.GET
    )
    public List<SelectiveUserDto> getUserWithSelectedFieldsWithSeparateRoles(@RequestParam List<String> fields){
        List<SelectiveRoleDto> roles = selectiveRoleService.findAll(fields);
        return selectiveUserService.findWithSeperateRoleRepoCall(fields, roles);
    }

    @RequestMapping(
            value = "querydsl_separate_roles",
            method = RequestMethod.GET
    )
    public List<SelectiveUserDto> getUsersWithSeparateRoles(@RequestParam List<String> fields, @RequestParam(required=false) String sortBy, @RequestParam(required=false) String sortOrder, @RequestParam Map<String,String> allRequestParams){
        List<SelectiveRoleDto> roles = selectiveRoleService.findAll(fields);
        return selectiveUserService.findUsersWithSelectedFieldsWithRoleRepoCall(fields, sortBy, sortOrder, allRequestParams, roles);
    }

    @RequestMapping(
            value = "criteria",
            method = RequestMethod.GET)
    public List<SelectiveUserDto> getUsersWithSelectedFieldsWithCriteria(@RequestParam List<String> fields, @RequestParam(required=false) String sortBy, @RequestParam(required=false) String sortOrder, @RequestParam(required=false) Long id, @RequestParam(required=false) String login) {
        return criteriaUserService.findUsersWithSelectedFieldsWithCriteria(fields, sortBy, sortOrder, id, login);
    }

    @RequestMapping(
            value = "criteria_separate_roles",
            method = RequestMethod.GET)
    public List<SelectiveUserDto> getUsersWithSelectedFieldsWithCriteriaWithRoles(@RequestParam List<String> fields, @RequestParam(required=false) String sortBy, @RequestParam(required=false) String sortOrder, @RequestParam Map<String,String> allRequestParams) {
        List<SelectiveRoleDto> roles = selectiveRoleService.findAllOld(fields);
        return selectiveUserService.findUsersWithSelectedFieldsWithCriteriaWithRoles(fields, sortBy, sortOrder, allRequestParams, roles);
    }
}
