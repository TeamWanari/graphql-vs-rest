package com.wanari.graphql.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Printer.class)
public class Printer_ {
    public static volatile SingularAttribute<Printer, Long> id;
    public static volatile SingularAttribute<Printer, String> name;
    public static volatile SingularAttribute<Printer, String> serialNumber;
    public static volatile SingularAttribute<Printer, User> owner;
}
