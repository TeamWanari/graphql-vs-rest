package com.wanari.graphql.resolver;

public class User {

    public Long id;
    public String login;

    public static User from(com.wanari.graphql.domain.User user) {
        User dto = new User();
        dto.id = user.id;
        dto.login = user.login;
        return dto;
    }

}
