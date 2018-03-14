package com.wanari.graphql.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Privilege {

    @Id
    public String key;

    @ManyToMany(mappedBy = "privileges", fetch= FetchType.LAZY)
    public Set<Role> roles;

}
