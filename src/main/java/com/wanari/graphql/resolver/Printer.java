package com.wanari.graphql.resolver;

import java.util.List;
import java.util.stream.Collectors;

public class Printer {
    public Long id;
    public Long userId;
    public String name;

    public static List<Printer> from(List<com.wanari.graphql.domain.Printer> printers) {
        return printers.stream().map(Printer::from).collect(Collectors.toList());
    }

    public static Printer from(com.wanari.graphql.domain.Printer printer) {
        Printer dto = new Printer();
        dto.id = printer.id;
        dto.name = printer.name;
        dto.userId = printer.owner.id;
        return dto;
    }
}

