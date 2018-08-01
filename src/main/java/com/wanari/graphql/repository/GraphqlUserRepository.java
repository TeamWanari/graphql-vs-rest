package com.wanari.graphql.repository;

import com.wanari.graphql.domain.User;
import com.wanari.graphql.filter.GenericFilterRepository;
import com.wanari.graphql.resolver.GraphqlUser;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphqlUserRepository extends GenericFilterRepository<GraphqlUser, User> {

}
