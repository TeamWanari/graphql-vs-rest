package com.wanari.graphql.resolver;

public class GraphqlUser {

    public Long id;
    public String login;

    public static GraphqlUser from(com.wanari.graphql.domain.User user) {
        GraphqlUser dto = new GraphqlUser();
        dto.id = user.id;
        dto.login = user.login;
        return dto;
    }

}
