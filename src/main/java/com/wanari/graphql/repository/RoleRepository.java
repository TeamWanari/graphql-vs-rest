package com.wanari.graphql.repository;

import com.wanari.graphql.controller.dto.RestRoleDto;
import com.wanari.graphql.domain.Role;
import com.wanari.graphql.filter.GenericFilterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, GenericFilterRepository<RestRoleDto, Role> {

    List<Role> findByUsersId(Long id);

    List<Role> findByPrivilegesKeyName(String key);
}
