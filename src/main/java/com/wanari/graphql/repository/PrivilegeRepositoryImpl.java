package com.wanari.graphql.repository;

import com.wanari.graphql.controller.dto.RestPrivilegeDto;
import com.wanari.graphql.domain.Privilege;
import com.wanari.graphql.domain.Privilege_;
import com.wanari.generic_filter.GenericFilterRepository;
import com.wanari.generic_filter.JoinTablesData;
import com.wanari.graphql.domain.constants.PrivilegeConstants;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class PrivilegeRepositoryImpl implements GenericFilterRepository<RestPrivilegeDto, Privilege> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EntityManager em() {
        return em;
    }

    @Override
    public Predicate getWhereConditions(CriteriaQuery<Privilege> query, CriteriaBuilder cb, Map<String, Object> filters, Root<Privilege> privilege) {
        List<Predicate> predicates = new ArrayList<>();

        return combinePredicates(predicates, cb::and);
    }

    @Override
    public SingularAttribute<Privilege, ?> getOrderByAttribute(String sortBy) {
        switch(sortBy) {
            case PrivilegeConstants.FieldName.KEY:
                return Privilege_.keyName;
            default:
                return Privilege_.keyName;
        }
    }

    @Override
    public void joinTables(FetchParent<?, Privilege> privilege, List<String> fields, JoinTablesData tables) {
    }
}
