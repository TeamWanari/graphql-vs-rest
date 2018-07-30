package com.wanari.graphql.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Privilege {

    @Id
    public String keyName;

    @ManyToMany(mappedBy = "privileges")
    public Set<Role> roles;

}
