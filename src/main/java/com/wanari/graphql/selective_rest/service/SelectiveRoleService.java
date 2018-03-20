package com.wanari.graphql.selective_rest.service;

import com.wanari.graphql.domain.Privilege;
import com.wanari.graphql.domain.Privilege_;
import com.wanari.graphql.domain.Role;
import com.wanari.graphql.domain.Role_;
import com.wanari.graphql.selective_rest.controller.dto.SelectiveRoleDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectiveRoleService {

    @PersistenceContext
    private EntityManager em;

    public List<SelectiveRoleDto> findAllOld(List<String> fields){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);
        Root<Role> role = criteriaQuery.from(Role.class);
//        SetAttribute<Role, Privilege> privileges = Role_.privileges;
//        Join<Role, Privilege> privis = role.join(privileges);
        role.fetch(Role_.privileges);
        criteriaQuery.select(role).distinct(true);
        TypedQuery<Role> typedQuery = em.createQuery(criteriaQuery);
        List<Role> allRoles = typedQuery.getResultList();
        return allRoles.stream().map(SelectiveRoleDto::from).collect(Collectors.toList());
    }

    public List<SelectiveRoleDto> findAll(List<String> fields){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = criteriaBuilder.createQuery(Role.class);

        Root<Privilege> privilegeRoot = criteriaQuery.from(Privilege.class);
//        Root<Privilege> privilege = criteriaQuery.from(Privilege.class);

        SetJoin<Privilege, Role> privileges = privilegeRoot.join(Privilege_.roles);

//        List<Predicate> conditions = new ArrayList();
//        conditions.add(criteriaBuilder.like(privileges.get("key"), "%PRIVI%"));

        CriteriaQuery<Role> cq = criteriaQuery.select(privileges).distinct(true);

        TypedQuery<Role> query = em.createQuery(cq);
//          em.createQuery(criteriaQuery)
//            .select(role)
//            .where(conditions.toArray(new Predicate[]{}))
//            .distinct(true));
        List<Role> allRoles = query.getResultList();
        return allRoles.stream().map(SelectiveRoleDto::from).collect(Collectors.toList());
    }
}
