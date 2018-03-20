package com.wanari.graphql.service;

import com.wanari.graphql.domain.User;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
