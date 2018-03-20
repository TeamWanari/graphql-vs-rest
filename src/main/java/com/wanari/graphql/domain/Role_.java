package com.wanari.graphql.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Role.class)
public class Role_ {
    public static volatile SingularAttribute<Role, String> key;
    public static volatile SetAttribute<Role, User> users;
    public static volatile SetAttribute<Role, Privilege> privileges;
}
