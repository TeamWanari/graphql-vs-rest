package com.wanari.graphql.service;

import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.domain.User;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wanari.graphql.controller.dto.RestUserDto.withSelectedFields;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllForRest() {
        return userRepository.findAllForRest();
    }

    public List<User> findAllForGraphql() {
        return userRepository.findAll();
    }

    public User findByUserId(Long userId) {
        return userRepository.findOne(userId);
    }

    public List<RestUserDto> findUsersWithSelectedFields(List<String> fields, Map<String,String> allRequestParams) {
        List<User> users = userRepository.findAllForRest();
        return users.stream().map(user -> withSelectedFields(user, fields, allRequestParams)).collect(Collectors.toList());
    }
}
