package com.wanari.graphql.selective_rest.service;

import com.wanari.graphql.domain.Role;
import com.wanari.graphql.domain.Role_;
import com.wanari.graphql.domain.User;
import com.wanari.graphql.domain.User_;
import com.wanari.graphql.selective_rest.controller.dto.SelectiveUserDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.List;
import java.util.stream.Collectors;

import static com.wanari.graphql.selective_rest.controller.dto.SelectiveUserDto.withSelectedFields;

@Service
public class CriteriaUserService {

    @PersistenceContext
    private EntityManager em;

    public List<SelectiveUserDto> findUsersWithSelectedFieldsWithCriteria(List<String> fields, String sortBy, String sortOrder, Long id, String login) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);

        Root<User> user = criteriaQuery.from(User.class);

        if(fields.contains("roles")) {
            Fetch<User, Role> roles = user.fetch(User_.roles);
            if(fields.contains("privileges")){
                roles.fetch(Role_.privileges);
            }
        }
//        if(fields.contains("printers")) {
//            user.fetch(User_.printer);
//        }

        SingularAttribute orderByAttribute = getOrderByAttribute(user, sortBy, sortOrder);
        Order order = getOrder(criteriaBuilder, orderByAttribute, sortOrder, user);
        Predicate where = getWhereConditions(criteriaBuilder, id, login, user);

        TypedQuery<User> typedQuery = em.createQuery(criteriaQuery
                .select(user)
                .orderBy(order)
                .where(where)
                .distinct(true)
        );
        List<User> allUsers = typedQuery.getResultList();
        return allUsers.stream().map(u -> withSelectedFields(u, fields)).collect(Collectors.toList());
    }

    private Predicate getWhereConditions(CriteriaBuilder criteriaBuilder, Long id, String login, Root<User> user) {
        Predicate base = criteriaBuilder.and();
        if(id != null){
            base = criteriaBuilder.and(base, criteriaBuilder.equal(user.get(User_.id), id));
        }
        if(login != null && !login.isEmpty()){
            base = criteriaBuilder.and(base, criteriaBuilder.like(user.get(User_.login), "%" + login + "%"));
        }
        return base;
    }

    private Order getOrder(CriteriaBuilder criteriaBuilder, SingularAttribute orderByAttribute, String sortOrder, Root<User> user) {
        if(sortOrder != null && !sortOrder.isEmpty()){
            switch(sortOrder){
                case "asc": return criteriaBuilder.asc(user.get(orderByAttribute));
                case "desc": return criteriaBuilder.desc(user.get(orderByAttribute));
                default: return criteriaBuilder.asc(user.get(orderByAttribute));
            }
        } else {
            return criteriaBuilder.asc(user.get(orderByAttribute));
        }
    }

    private SingularAttribute getOrderByAttribute(Path<User> user, String sortBy, String sortOrder){
        if(sortBy != null && !sortBy.isEmpty()){
            switch(sortBy){
                case "id": return User_.id;
                case "login": return User_.login;
                default: return User_.login;
            }
        } else {
            return User_.login;
        }
    }
}
