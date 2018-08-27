package com.wanari.graphql.repository;

import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.domain.User;
import com.wanari.generic_filter.GenericFilterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, GenericFilterRepository<RestUserDto, User> {

    @Query(" SELECT DISTINCT user from User user " +
        " INNER JOIN FETCH user.roles role " +
        " INNER JOIN FETCH role.privileges pivi " +
        " LEFT JOIN FETCH user.printers prin ")
    List<User> findAllJoined();

    @Query(" SELECT DISTINCT user from User user " +
        " INNER JOIN FETCH user.roles role " +
        " INNER JOIN FETCH role.privileges pivi " +
        " LEFT JOIN FETCH user.printers prin " +
        " WHERE user.id = :id")
    User findOneJoined(@Param("id") Long id);

    List<User> findByRolesKeyName(String key);
}
