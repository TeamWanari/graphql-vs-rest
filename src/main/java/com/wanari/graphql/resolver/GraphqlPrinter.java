package com.wanari.graphql.resolver;

import com.wanari.graphql.domain.Printer;

import java.util.List;
import java.util.stream.Collectors;

public class GraphqlPrinter {
    public Long id;
    public Long ownerId;
    public String name;
    public String serialNumber;

    public static List<GraphqlPrinter> from(List<Printer> printers) {
        return printers.stream().map(GraphqlPrinter::from).collect(Collectors.toList());
    }

    public static GraphqlPrinter from(Printer printer) {
        GraphqlPrinter dto = new GraphqlPrinter();
        dto.id = printer.id;
        dto.name = printer.name;
        dto.serialNumber = printer.serialNumber;
        dto.ownerId = printer.owner.id;
        return dto;
    }
}

