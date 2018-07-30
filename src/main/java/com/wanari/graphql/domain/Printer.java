package com.wanari.graphql.domain;

import javax.persistence.*;

@Entity
public class Printer {

    @Id
    @GeneratedValue
    public Long id;

    @ManyToOne
    public User owner;

    @Column
    public String name;

    @Column
    public String serialNumber;

}
