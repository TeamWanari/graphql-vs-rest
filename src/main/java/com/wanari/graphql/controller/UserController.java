package com.wanari.graphql.controller;

import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
