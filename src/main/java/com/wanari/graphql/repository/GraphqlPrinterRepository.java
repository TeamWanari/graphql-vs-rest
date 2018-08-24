package com.wanari.graphql.repository;

import com.wanari.graphql.domain.Printer;
import com.wanari.generic_filter.GenericFilterRepository;
import com.wanari.graphql.resolver.GraphqlPrinter;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphqlPrinterRepository extends GenericFilterRepository<GraphqlPrinter, Printer> {

}
