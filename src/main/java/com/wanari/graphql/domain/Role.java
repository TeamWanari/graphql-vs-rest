package com.wanari.graphql.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role {

    @Id
    public String key;

    @ManyToMany(mappedBy = "roles", fetch=FetchType.LAZY)
    public Set<User> users;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "role_key"), inverseJoinColumns = @JoinColumn(name = "privilege_key"))
    public Set<Privilege> privileges;


}
