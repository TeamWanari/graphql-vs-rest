package com.wanari.graphql.selective_rest.domain_querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.SetPath;
import com.querydsl.core.types.dsl.StringPath;
import com.wanari.graphql.domain.Privilege;
import com.wanari.graphql.domain.Role;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QPrivilege is a Querydsl query type for Privilege
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrivilege extends EntityPathBase<Privilege> {

    private static final long serialVersionUID = 1299819673L;

    public static final QPrivilege privilege = new QPrivilege("privilege");

    public final StringPath key = createString("key");

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public QPrivilege(String variable) {
        super(Privilege.class, forVariable(variable));
    }

    public QPrivilege(Path<? extends Privilege> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPrivilege(PathMetadata metadata) {
        super(Privilege.class, metadata);
    }

}

