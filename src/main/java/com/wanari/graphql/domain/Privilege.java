package com.wanari.graphql.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Privilege {

    @Id
    public String key;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "privilege_key"), inverseJoinColumns = @JoinColumn(name = "role_key"))
    public Set<Role> roles;

}
