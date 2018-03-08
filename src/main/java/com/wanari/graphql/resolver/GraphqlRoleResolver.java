package com.wanari.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.wanari.graphql.repository.PrivilegeRepository;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphqlRoleResolver implements GraphQLResolver<GraphqlRole> {

    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;

    public GraphqlRoleResolver(UserRepository userRepository, PrivilegeRepository privilegeRepository) {
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
    }

    public List<GraphqlUser> getUsers(GraphqlRole role) {
        return GraphqlUser.from(userRepository.findByRolesKey(role.key));
    }

    public List<GraphqlPrivilege> getPrivileges(GraphqlRole role) {
        return GraphqlPrivilege.from(privilegeRepository.findByRolesKey(role.key));
    }
}
