package com.wanari.graphql.controller;

import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.filter.GenericFilter;
import com.wanari.graphql.filter.GenericParameterBuilder;
import com.wanari.graphql.filter.GenericParameters;
import com.wanari.graphql.filter.constants.GeneralFilterConstants;
import com.wanari.graphql.filter.constants.UserConstants;
import com.wanari.graphql.repository.UserRepository;
import com.wanari.graphql.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(
        value = "",
        method = RequestMethod.GET)
    public List<RestUserDto> getAllUser() {
        return userRepository.findAll().stream().map(RestUserDto::from).collect(Collectors.toList());
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET)
    public RestUserDto getUserById(@PathVariable("id") Long id) {
        return RestUserDto.from(userRepository.findOne(id));
    }

    @RequestMapping(
        value = "/joined",
        method = RequestMethod.GET)
    public List<RestUserDto> getAllUserJoined() {
        return userRepository.findAllJoined().stream().map(RestUserDto::from).collect(Collectors.toList());
    }

    @RequestMapping(
        value = "/joined/{id}",
        method = RequestMethod.GET)
    public RestUserDto getUserByIdJoined(@PathVariable("id") Long id) {
        return RestUserDto.from(userRepository.findOneJoined(id));
    }

    @RequestMapping(
        value = "/filtered",
        method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserFiltered(@RequestParam List<String> fields, @RequestParam Map<String, Object> allRequestParams) {

        GenericParameters parameter = GenericParameterBuilder
            .filterWith(toFilter(allRequestParams))
            .sortBy(allRequestParams.getOrDefault(GeneralFilterConstants.SORT_BY, UserConstants.FieldName.ID))
            .sortOrder(allRequestParams.getOrDefault(GeneralFilterConstants.SORT_ORDER, GeneralFilterConstants.ASC))
            .page(allRequestParams.getOrDefault(GeneralFilterConstants.PAGE, GeneralFilterConstants.DEFAULT_PAGE))
            .pageSize(allRequestParams.getOrDefault(GeneralFilterConstants.PAGE_SIZE, GeneralFilterConstants.DEFAULT_PAGE_SIZE))
            .withFields(fields)
            .joinTables(false)
            .build();

        return parameter.validate(UserConstants.ENTITY_NAME).map(userService::find).fold(
            this::errorToResponse,
            this::toResponse
        );
    }

    @RequestMapping(
        value = "/filtered/joined",
        method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserFilteredAndJoined(@RequestParam List<String> fields, @RequestParam Map<String, Object> allRequestParams) {

        GenericParameters parameter = GenericParameterBuilder
            .filterWith(toFilter(allRequestParams))
            .sortBy(allRequestParams.getOrDefault(GeneralFilterConstants.SORT_BY, UserConstants.FieldName.ID))
            .sortOrder(allRequestParams.getOrDefault(GeneralFilterConstants.SORT_ORDER, GeneralFilterConstants.ASC))
            .page(allRequestParams.getOrDefault(GeneralFilterConstants.PAGE, GeneralFilterConstants.DEFAULT_PAGE))
            .pageSize(allRequestParams.getOrDefault(GeneralFilterConstants.PAGE_SIZE, GeneralFilterConstants.DEFAULT_PAGE_SIZE))
            .withFields(fields)
            .joinTables(true)
            .build();

        return parameter.validate(UserConstants.ENTITY_NAME).map(userService::find).fold(
            this::errorToResponse,
            this::toResponse
        );
    }

    private GenericFilter toFilter(Map<String, Object> allParams) {
        GenericFilter filter = new GenericFilter();

        filter.on(UserConstants.FieldName.ID)
            .withValue(allParams.get(UserConstants.FieldName.ID))
            .onlyIfNotNull()
            .addFilter();

        return filter;
    }

}
