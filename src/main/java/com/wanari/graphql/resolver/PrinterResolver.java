package com.wanari.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.wanari.graphql.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class PrinterResolver implements GraphQLResolver<Printer> {

    private final UserRepository userRepository;

    public PrinterResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Printer printer) {
        return User.from(userRepository.findOne(printer.userId));
    }
}
