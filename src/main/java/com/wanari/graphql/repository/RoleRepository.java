package com.wanari.graphql.repository;

import com.wanari.graphql.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByUsersId(Long id);

    List<Role> findByPrivilegesKey(String key);
}
