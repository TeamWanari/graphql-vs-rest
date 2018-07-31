package com.wanari.graphql.repository;

import com.wanari.graphql.controller.dto.RestPrinterDto;
import com.wanari.graphql.domain.Printer;
import com.wanari.graphql.filter.GenericFilterRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrinterRepository extends JpaRepository<Printer, Long>, GenericFilterRepository<RestPrinterDto, Printer> {

    @Query("SELECT DISTINCT printer FROM Printer printer" +
        " INNER JOIN FETCH printer.owner ")
    List<Printer> findAllJoined();

    @Query("SELECT DISTINCT printer FROM Printer printer" +
        " INNER JOIN FETCH printer.owner " +
        "WHERE printer.id = :id")
    Printer findOneJoined(@Param("id") Long id);

    List<Printer> findByOwnerId(Long id);

    @Query(" SELECT printer from Printer printer " +
        " INNER JOIN FETCH printer.owner owner" +
        " WHERE owner.id =:id ")
    List<Printer> findByOwnerIdJoined(@Param("id") Long id);

    List<Printer> findByIdAndOwnerId(Long id, Long ownerId);
}
