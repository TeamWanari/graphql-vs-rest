package com.wanari.graphql.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Privilege.class)
public class Privilege_ {

    public static volatile SingularAttribute<Privilege, String> key;
    public static volatile SetAttribute<Privilege, Role> roles;
}
