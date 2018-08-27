package com.wanari.graphql.repository;

import com.wanari.graphql.domain.Printer;
import com.wanari.graphql.resolver.GraphqlPrinter;
import com.wanari.utils.spring.genericfilter.GenericFilterRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphqlPrinterRepository extends GenericFilterRepository<GraphqlPrinter, Printer> {

}
