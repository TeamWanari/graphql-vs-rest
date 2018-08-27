package com.wanari.graphql.repository;

import com.wanari.graphql.controller.dto.RestPrivilegeDto;
import com.wanari.graphql.domain.Privilege;
import com.wanari.generic_filter.GenericFilterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivilegeRepository extends JpaRepository<Privilege, Long>, GenericFilterRepository<RestPrivilegeDto, Privilege> {
    List<Privilege> findByRolesKeyName(String key);
}
