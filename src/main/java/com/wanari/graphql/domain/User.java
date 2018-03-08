package com.wanari.graphql.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    public Long id;

    @Column
    public String login;

    @Column
    public String password;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_key"))
    public Set<Role> roles;

    @OneToMany(mappedBy = "owner")
    public Set<Printer> printers;

}
