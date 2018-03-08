package com.wanari.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class GraphqlPrinterResolver implements GraphQLResolver<GraphqlPrinter> {

    private final UserRepository userRepository;

    public GraphqlPrinterResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GraphqlUser getOwner(GraphqlPrinter printer) {
        return GraphqlUser.from(userRepository.findOne(printer.ownerId));
    }
}
