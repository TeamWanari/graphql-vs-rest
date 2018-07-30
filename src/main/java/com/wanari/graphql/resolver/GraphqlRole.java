package com.wanari.graphql.resolver;

import com.wanari.graphql.domain.Role;

import java.util.List;
import java.util.stream.Collectors;

public class GraphqlRole {

    public String key;

    public static List<GraphqlRole> from(List<Role> roles) {
        return roles.stream().map(GraphqlRole::from).collect(Collectors.toList());
    }

    public static GraphqlRole from(Role role) {
        GraphqlRole dto = new GraphqlRole();
        dto.key = role.keyName;
//        TODO cannot do this because of 'org.hibernate.LazyInitializationException'.
//        dto.privileges = GraphqlPrivilege.from(role.privileges);
        return dto;
    }
}
