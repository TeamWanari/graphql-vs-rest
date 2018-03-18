package com.wanari.graphql.selective_rest.domain_querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.StringPath;
import com.wanari.graphql.domain.Privilege;
import com.wanari.graphql.domain.Role;
import com.wanari.graphql.domain.User;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QRole is a Querydsl query type for Role
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRole extends EntityPathBase<Role> {

    private static final long serialVersionUID = -863585906L;

    public static final QRole role = new QRole("role");

    public final StringPath key = createString("key");

    public final SetPath<Privilege, QPrivilege> privileges = this.<Privilege, QPrivilege>createSet("privileges", Privilege.class, QPrivilege.class, PathInits.DIRECT2);

    public final SetPath<User, QUser> users = this.<User, QUser>createSet("users", User.class, QUser.class, PathInits.DIRECT2);

    public QRole(String variable) {
        super(Role.class, forVariable(variable));
    }

    public QRole(Path<? extends Role> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRole(PathMetadata metadata) {
        super(Role.class, metadata);
    }

}

