package com.wanari.graphql.repository;

import com.wanari.graphql.domain.User;
import com.wanari.graphql.domain.User_;
import com.wanari.graphql.filter.JoinTablesData;
import com.wanari.graphql.filter.constants.UserConstants;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class GraphqlUserRepositoryImpl implements GraphqlUserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EntityManager em() {
        return em;
    }

    @Override
    public Predicate getWhereConditions(CriteriaQuery<User> query, CriteriaBuilder cb, Map<String, Object> filters, Root<User> user) {
        List<Predicate> predicates = new ArrayList<>();

        if(filters.containsKey(UserConstants.FieldName.ID)) {
            predicates.add(cb.equal(user.get(User_.id), filters.get(UserConstants.FieldName.ID)));
        }

        return combinePredicates(predicates, cb::and);
    }

    @Override
    public SingularAttribute<User, ?> getOrderByAttribute(String sortBy) {
        switch(sortBy) {
            case UserConstants.FieldName.ID:
                return User_.id;
            case UserConstants.FieldName.LOGIN:
                return User_.login;
            default:
                return User_.id;
        }
    }

    @Override
    public void joinTables(FetchParent<?, User> user, List<String> fields, JoinTablesData tables) {
        // No needed for GraphQL
    }
}
