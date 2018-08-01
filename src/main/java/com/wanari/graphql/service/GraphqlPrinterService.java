package com.wanari.graphql.service;

import com.wanari.graphql.domain.Printer;
import com.wanari.graphql.filter.ValidGenericParameters;
import com.wanari.graphql.repository.GraphqlPrinterRepository;
import com.wanari.graphql.resolver.GraphqlPrinter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphqlPrinterService {

    private final GraphqlPrinterRepository printerRepository;

    public GraphqlPrinterService(GraphqlPrinterRepository printerRepository) {
        this.printerRepository = printerRepository;
    }

    public List<GraphqlPrinter> find(ValidGenericParameters validParameter) {
        return printerRepository.findForGraphql(validParameter, Printer.class, GraphqlPrinter::from);
    }

}
