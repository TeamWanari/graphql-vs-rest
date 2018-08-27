package com.wanari.graphql.repository;

import com.wanari.graphql.domain.User;
import com.wanari.graphql.resolver.GraphqlUser;
import com.wanari.utils.spring.genericfilter.GenericFilterRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphqlUserRepository extends GenericFilterRepository<GraphqlUser, User> {

}
