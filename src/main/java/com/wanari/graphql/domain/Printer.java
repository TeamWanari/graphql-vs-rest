package com.wanari.graphql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Printer {

    @Id
    public Long id;

    @ManyToOne
    public User owner;

    @Column
    public String name;

    @Column
    public String serialNumber;

}
