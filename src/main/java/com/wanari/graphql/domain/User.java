package com.wanari.graphql.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {

    @Id
    public Long id;

    @Column
    public String login;

    @Column
    public String password;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_key"))
    public Set<Role> roles;

    @OneToMany(mappedBy = "owner", fetch=FetchType.LAZY)
    public List<Printer> printer;

}
