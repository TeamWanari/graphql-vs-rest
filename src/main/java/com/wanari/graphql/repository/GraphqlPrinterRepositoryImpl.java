package com.wanari.graphql.repository;

import com.wanari.graphql.domain.Printer;
import com.wanari.graphql.domain.Printer_;
import com.wanari.graphql.domain.User_;
import com.wanari.graphql.domain.constants.PrinterConstants;
import com.wanari.utils.spring.genericfilter.JoinTablesData;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class GraphqlPrinterRepositoryImpl implements GraphqlPrinterRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public EntityManager em() {
        return em;
    }

    @Override
    public Predicate getWhereConditions(CriteriaQuery<Printer> query, CriteriaBuilder cb, Map<String, Object> filters, Root<Printer> printer) {
        List<Predicate> predicates = new ArrayList<>();

        if(filters.containsKey(PrinterConstants.FieldName.ID)) {
            predicates.add(cb.equal(printer.get(Printer_.id), filters.get(PrinterConstants.FieldName.ID)));
        }

        if(filters.containsKey(PrinterConstants.FieldName.OWNER_ID)) {
            predicates.add(cb.equal(printer.get(Printer_.owner).get(User_.id), filters.get(PrinterConstants.FieldName.OWNER_ID)));
        }

        return combinePredicates(predicates, cb::and);
    }

    @Override
    public SingularAttribute<Printer, ?> getOrderByAttribute(String sortBy) {
        switch(sortBy) {
            case PrinterConstants.FieldName.ID:
                return Printer_.id;
            default:
                return Printer_.id;
        }
    }

    @Override
    public void joinTables(FetchParent<?, Printer> printer, List<String> fields, JoinTablesData tables) {
        // No needed for GraphQL
    }
}
