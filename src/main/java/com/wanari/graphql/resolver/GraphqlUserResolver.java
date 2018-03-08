package com.wanari.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.wanari.graphql.repository.PrinterRepository;
import com.wanari.graphql.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphqlUserResolver implements GraphQLResolver<GraphqlUser> {

    private final PrinterRepository printerRepository;
    private final RoleRepository roleRepository;

    public GraphqlUserResolver(PrinterRepository printerRepository, RoleRepository roleRepository) {
        this.printerRepository = printerRepository;
        this.roleRepository = roleRepository;
    }

    public List<GraphqlPrinter> getPrinters(GraphqlUser user) {
        return GraphqlPrinter.from(printerRepository.findByOwnerId(user.id));
    }

    public List<GraphqlRole> getRoles(GraphqlUser user) {
        return GraphqlRole.from(roleRepository.findByUsersId(user.id));
    }
}
