package com.wanari.graphql.domain;

import javax.persistence.*;

@Entity
public class Printer {

    @Id
    public Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    public User owner;

    @Column
    public String name;

    @Column
    public String serialNumber;

}
