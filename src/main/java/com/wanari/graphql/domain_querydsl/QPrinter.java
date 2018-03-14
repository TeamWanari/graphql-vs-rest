package com.wanari.graphql.domain_querydsl;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import com.wanari.graphql.domain.Printer;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;


/**
 * QPrinter is a Querydsl query type for Printer
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPrinter extends EntityPathBase<Printer> {

    private static final long serialVersionUID = -1925130238L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPrinter printer = new QPrinter("printer");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final QUser owner;

    public final StringPath serialNumber = createString("serialNumber");

    public QPrinter(String variable) {
        this(Printer.class, forVariable(variable), INITS);
    }

    public QPrinter(Path<? extends Printer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPrinter(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPrinter(PathMetadata metadata, PathInits inits) {
        this(Printer.class, metadata, inits);
    }

    public QPrinter(Class<? extends Printer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.owner = inits.isInitialized("owner") ? new QUser(forProperty("owner")) : null;
    }

}

