package com.wanari.graphql.repository;

import com.wanari.graphql.controller.dto.RestRoleDto;
import com.wanari.graphql.domain.Privilege;
import com.wanari.graphql.domain.Role;
import com.wanari.graphql.domain.Role_;
import com.wanari.generic_filter.GenericFilterRepository;
import com.wanari.generic_filter.GenericFilterUtil;
import com.wanari.generic_filter.JoinTablesData;
import com.wanari.graphql.domain.constants.RoleConstants;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class RoleRepositoryImpl implements GenericFilterRepository<RestRoleDto, Role> {

    private final PrivilegeRepository privilegeRepository;

    @PersistenceContext
    private EntityManager em;

    public RoleRepositoryImpl(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public EntityManager em() {
        return em;
    }

    @Override
    public Predicate getWhereConditions(CriteriaQuery<Role> query, CriteriaBuilder cb, Map<String, Object> filters, Root<Role> role) {
        List<Predicate> predicates = new ArrayList<>();

        return combinePredicates(predicates, cb::and);
    }

    @Override
    public SingularAttribute<Role, ?> getOrderByAttribute(String sortBy) {
        switch(sortBy) {
            case RoleConstants.FieldName.KEY:
                return Role_.keyName;
            default:
                return Role_.keyName;
        }
    }

    @Override
    public void joinTables(FetchParent<?, Role> role, List<String> fields, JoinTablesData tables) {
        if(fields.contains(RoleConstants.FieldName.PRIVILEGES)) {
            Fetch<Role, Privilege> roles = tables.leftJoinFetch(role, RoleConstants.JoinInfo.PRIVILEGES);
            List<String> roleFields = GenericFilterUtil.getNestedFields(fields, RoleConstants.ENTITY_NAME);
            privilegeRepository.joinTables(roles, roleFields, tables);
        }
    }
}
