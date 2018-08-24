package com.wanari.graphql.service;

import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.domain.User;
import com.wanari.generic_filter.ValidGenericParameters;
import com.wanari.graphql.domain.constants.UserConstants;
import com.wanari.graphql.mapper.UserMapper;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<RestUserDto> find(ValidGenericParameters validParameter) {
        if(validParameter.fields.contains(UserConstants.Wrapper.DETAILS)) {
            validParameter.fields.addAll(UserConstants.details);
        }
        return userRepository.find(validParameter, User.class, userMapper::mapWithFields);
    }

}
