package com.wanari.graphql.repository;

import com.wanari.graphql.domain.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
    List<Privilege> findByRolesKey(String key);
}
