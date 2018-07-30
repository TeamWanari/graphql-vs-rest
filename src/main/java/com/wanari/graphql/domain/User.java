package com.wanari.graphql.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue
    public Long id;

    @Column
    public String login;

    @Column
    public String password;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_key"))
    public Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "owner")
    public Set<Printer> printers = new HashSet<>();

}
