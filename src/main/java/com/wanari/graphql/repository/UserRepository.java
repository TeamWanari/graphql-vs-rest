package com.wanari.graphql.repository;

import com.wanari.graphql.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, QueryDslPredicateExecutor<User> {

    @Query(" SELECT DISTINCT user from User user " + //TODO multi returnöl ez a buzi
        " INNER JOIN FETCH user.roles role " +
        " INNER JOIN FETCH role.privileges pivi " +
        " INNER JOIN FETCH user.printer prin ")
    List<User> findAllForRest();
}
