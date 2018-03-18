package com.wanari.graphql.selective_rest;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.ComparableExpressionBase;
import com.querydsl.jpa.impl.JPAQuery;
import com.wanari.graphql.domain.User;
import com.wanari.graphql.repository.UserRepository;
import com.wanari.graphql.selective_rest.controller.dto.SelectiveUserDto;
import com.wanari.graphql.selective_rest.domain_querydsl.QPrivilege;
import com.wanari.graphql.selective_rest.domain_querydsl.QRole;
import com.wanari.graphql.selective_rest.domain_querydsl.QUser;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.wanari.graphql.selective_rest.controller.dto.SelectiveUserDto.withSelectedFields;

@Service
public class SelectiveUserService {

    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;

    public SelectiveUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllForRest() {
        return userRepository.findAllForRest();
    }

    //Works just fine but no
    public List<SelectiveUserDto> findAll(List<String> fields){
        List<User> users = userRepository.findAllForRest();
        return users.stream().map(u -> withSelectedFields(u, fields)).collect(Collectors.toList());
    }

    public List<SelectiveUserDto> findAllHacky(List<String> fields){
        String queryString = " SELECT DISTINCT user from User user " + //TODO multi returnöl ez a buzi
                " INNER JOIN FETCH user.roles role " +
                " INNER JOIN FETCH role.privileges pivi";

        JPAQuery<User> query = new JPAQuery<>(em);
        //TODO: NATIVE sql?
//        List<User> users = query.from(user)
        List<User> users = userRepository.findAllForRest();
        return users.stream().map(u -> withSelectedFields(u, fields)).collect(Collectors.toList());
    }

    public List<SelectiveUserDto> findUsersWithSelectedFields(List<String> fields, String sortBy, String sortOrder, Map<String,String> allRequestParams) {
        QUser user = QUser.user;
        QRole role = QRole.role;
        QPrivilege privilege = QPrivilege.privilege;
        JPAQuery<User> query = new JPAQuery<>(em);
//        List<User> users = query.from(user).distinct().orderBy(user.login.desc()).fetch();
        List<User> users = setOrderBy(query.from(user).innerJoin(user.roles, role).innerJoin(role.privileges, privilege).distinct(), user, sortBy, sortOrder).fetch();
        return users.stream().map(u -> withSelectedFields(u, fields)).collect(Collectors.toList());
    }

    private JPAQuery<User> setOrderBy(JPAQuery<User> query, QUser user, String sortBy, String sortOrder) {
        if(sortBy != null && !sortBy.isEmpty()){
            switch(sortBy){
                case "id": return(query.orderBy(ascOrDesc(user.id, sortOrder)));
                case "login": return(query.orderBy(ascOrDesc(user.login, sortOrder)));
                default: return query;
            }
        } else {
            return query;
        }
    }

    private OrderSpecifier<?> ascOrDesc(ComparableExpressionBase<?> path, String sortOrder) {
        if(sortOrder != null && !sortOrder.isEmpty()){
            switch(sortOrder){
                case "asc": return path.asc();
                case "desc": return path.desc();
                default: return path.asc();
            }
        } else {
            return path.asc();
        }
    }
}
