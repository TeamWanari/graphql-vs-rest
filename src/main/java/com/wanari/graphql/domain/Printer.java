package com.wanari.graphql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Printer {

    @Id
    public Long id;

    @OneToOne
    public User owner;

    @Column
    public String name;

    @Column
    public String serialNumber;

}
