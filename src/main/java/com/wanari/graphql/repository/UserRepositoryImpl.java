package com.wanari.graphql.repository;

import com.wanari.graphql.controller.dto.RestUserDto;
import com.wanari.graphql.domain.Printer;
import com.wanari.graphql.domain.Role;
import com.wanari.graphql.domain.User;
import com.wanari.graphql.domain.User_;
import com.wanari.generic_filter.GenericFilterRepository;
import com.wanari.generic_filter.GenericFilterUtil;
import com.wanari.generic_filter.JoinTablesData;
import com.wanari.graphql.domain.constants.PrinterConstants;
import com.wanari.graphql.domain.constants.RoleConstants;
import com.wanari.graphql.domain.constants.UserConstants;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class UserRepositoryImpl implements GenericFilterRepository<RestUserDto, User> {

    private final RoleRepository roleRepository;
    private final PrinterRepository printerRepository;

    @PersistenceContext
    private EntityManager em;

    public UserRepositoryImpl(RoleRepository roleRepository, PrinterRepository printerRepository) {
        this.roleRepository = roleRepository;
        this.printerRepository = printerRepository;
    }

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
        if(fields.contains(UserConstants.FieldName.ROLES)) {
            Fetch<User, Role> roles = tables.leftJoinFetch(user, UserConstants.JoinInfo.ROLES);
            List<String> roleFields = GenericFilterUtil.getNestedFields(fields, RoleConstants.ENTITY_NAME);
            roleRepository.joinTables(roles, roleFields, tables);
        }
        if(fields.contains(UserConstants.FieldName.PRINTERS)) {
            Fetch<User, Printer> printers = tables.leftJoinFetch(user, UserConstants.JoinInfo.PRINTERS);
            List<String> printersFields = GenericFilterUtil.getNestedFields(fields, PrinterConstants.ENTITY_NAME);
            printerRepository.joinTables(printers, printersFields, tables);
        }
    }
}
