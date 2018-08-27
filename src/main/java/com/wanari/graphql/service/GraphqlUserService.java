package com.wanari.graphql.service;

import com.wanari.graphql.domain.User;
import com.wanari.generic_filter.ValidGenericParameters;
import com.wanari.graphql.repository.GraphqlUserRepository;
import com.wanari.graphql.resolver.GraphqlUser;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphqlUserService {

    private final GraphqlUserRepository userRepository;

    public GraphqlUserService(GraphqlUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<GraphqlUser> find(ValidGenericParameters validParameter) {
        return userRepository.findForGraphql(validParameter, User.class, GraphqlUser::from);
    }

}
