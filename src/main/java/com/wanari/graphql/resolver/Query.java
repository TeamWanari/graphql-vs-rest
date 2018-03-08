package com.wanari.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.wanari.graphql.repository.PrinterRepository;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final PrinterRepository printerRepository;
    private final UserRepository userRepository;

    public Query(PrinterRepository printerRepository, UserRepository userRepository) {
        this.printerRepository = printerRepository;
        this.userRepository = userRepository;
    }

    public List<GraphqlPrinter> getPrinters(Long id, Long ownerId) {
        if(id != null && ownerId != null) {
            return GraphqlPrinter.from(printerRepository.findByIdAndOwnerId(id, ownerId));
        } else if(id != null) {
            return GraphqlPrinter.from(printerRepository.findByOwnerId(id));
        } else if(ownerId != null) {
            return Collections.singletonList(GraphqlPrinter.from(printerRepository.findOne(ownerId)));
        } else {
            return GraphqlPrinter.from(printerRepository.findAll());
        }
    }

    public List<GraphqlUser> getUsers(Long id) {
        if(id == null) {
            return GraphqlUser.from(userRepository.findAll());
        } else {
            return Collections.singletonList(
                GraphqlUser.from(userRepository.findOne(id))
            );
        }
    }
}
