package com.wanari.graphql.service;

import com.wanari.graphql.domain.Role;
import com.wanari.graphql.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findByUserId(Long id) {
        return roleRepository.findAll(); //TODO filter
    }

    public List<Role> findAllForGraphql() {
        return roleRepository.findAll();
    }
}
