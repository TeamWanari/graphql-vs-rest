package com.wanari.graphql.resolver;

import com.wanari.graphql.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class GraphqlUser {
    public Long id;
    public String login;

    public static List<GraphqlUser> from(List<User> users) {
        return users.stream().map(GraphqlUser::from).collect(Collectors.toList());
    }

    public static GraphqlUser from(User user) {
        GraphqlUser dto = new GraphqlUser();
        dto.id = user.id;
        dto.login = user.login;
//        TODO cannot do this because of 'org.hibernate.LazyInitializationException'.
//        dto.printers = GraphqlPrinter.from(user.printers);
//        dto.roles = GraphqlRole.from(user.roles);
        return dto;
    }

}
