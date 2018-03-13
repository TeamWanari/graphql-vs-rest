package com.wanari.graphql.resolver;

import java.util.List;
import java.util.stream.Collectors;

public class GraphqlPrinter {
    public Long id;
    public Long userId;
    public String name;

    public static List<GraphqlPrinter> from(List<com.wanari.graphql.domain.Printer> printers) {
        return printers.stream().map(GraphqlPrinter::from).collect(Collectors.toList());
    }

    public static GraphqlPrinter from(com.wanari.graphql.domain.Printer printer) {
        GraphqlPrinter dto = new GraphqlPrinter();
        dto.id = printer.id;
        dto.name = printer.name;
        dto.userId = printer.owner.id;
        return dto;
    }
}

