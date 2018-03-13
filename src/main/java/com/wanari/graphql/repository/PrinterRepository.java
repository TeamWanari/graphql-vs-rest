package com.wanari.graphql.repository;

import com.wanari.graphql.domain.Printer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long> {
    @Query(" SELECT p from Printer p " +
        " INNER JOIN FETCH p.owner ")
    List<Printer> findForGraphql();
}
