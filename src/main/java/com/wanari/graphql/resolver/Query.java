package com.wanari.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.wanari.graphql.repository.PrinterRepository;
import com.wanari.graphql.service.RoleService;
import com.wanari.graphql.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final UserService userService;
    private final RoleService roleService;
    private final PrinterRepository printerRepository;

    public Query(UserService userService, RoleService roleService, PrinterRepository printerRepository) {
        this.userService = userService;
        this.roleService = roleService;
        this.printerRepository = printerRepository;
    }

    public List<GraphqlPrinter> getPrinters(Integer thaParam) {
        return GraphqlPrinter.from(printerRepository.findForGraphql());
    }

}
