package com.wanari.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.wanari.graphql.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphqlPrivilegeResolver implements GraphQLResolver<GraphqlPrivilege> {

    private final RoleRepository roleRepository;

    public GraphqlPrivilegeResolver(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<GraphqlRole> getRoles(GraphqlPrivilege privilege) {
        return GraphqlRole.from(roleRepository.findByPrivilegesKey(privilege.key));
    }

}
