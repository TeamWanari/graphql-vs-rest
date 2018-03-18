package com.wanari.graphql.service;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.domain.User;
import com.wanari.graphql.selective_rest.domain_querydsl.QPrivilege;
import com.wanari.graphql.selective_rest.domain_querydsl.QRole;
import com.wanari.graphql.selective_rest.domain_querydsl.QUser;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
