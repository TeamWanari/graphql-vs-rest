package com.wanari.graphql.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    public String key;

    @ManyToMany(mappedBy = "roles")
    public Set<User> users;

    @ManyToMany
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "role_key"), inverseJoinColumns = @JoinColumn(name = "privilege_key"))
    public Set<Privilege> privileges;


}
