package com.wanari.graphql.resolver;

import com.wanari.graphql.domain.Privilege;

import java.util.List;
import java.util.stream.Collectors;

public class GraphqlPrivilege {

    public String key;

    public static List<GraphqlPrivilege> from(List<Privilege> privileges) {
        return privileges.stream().map(GraphqlPrivilege::from).collect(Collectors.toList());
    }

    public static GraphqlPrivilege from(Privilege privilege) {
        GraphqlPrivilege dto = new GraphqlPrivilege();
        dto.key = privilege.key;
//        TODO cannot do this because of 'org.hibernate.LazyInitializationException'.
//        dto.roles = GraphqlRole.from(privilege.roles);
        return dto;
    }
}
