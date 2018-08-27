package com.wanari.graphql.controller;


import com.wanari.graphql.controller.dto.RestPrinterDto;
import com.wanari.generic_filter.GenericFilter;
import com.wanari.generic_filter.GenericParameterBuilder;
import com.wanari.generic_filter.GenericParameters;
import com.wanari.generic_filter.constants.GeneralFilterConstants;
import com.wanari.graphql.domain.constants.PrinterConstants;
import com.wanari.graphql.repository.PrinterRepository;
import com.wanari.graphql.service.PrinterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/printer")
public class PrinterController extends BaseController {

    private final PrinterRepository printerRepository;
    private final PrinterService printerService;

    public PrinterController(PrinterRepository printerRepository, PrinterService printerService) {
        this.printerRepository = printerRepository;
        this.printerService = printerService;
    }

    @RequestMapping(
        value = "",
        method = RequestMethod.GET)
    public List<RestPrinterDto> getAllPrinter() {
        return printerRepository.findAll().stream().map(RestPrinterDto::from).collect(Collectors.toList());
    }

    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET)
    public RestPrinterDto getPrinterById(@PathVariable("id") Long id) {
        return RestPrinterDto.from(printerRepository.findOne(id));
    }

    @RequestMapping(
        value = "/owner/{id}",
        method = RequestMethod.GET)
    public List<RestPrinterDto> getPrinterByOwnerId(@PathVariable("id") Long id) {
        return printerRepository.findByOwnerId(id).stream().map(RestPrinterDto::from).collect(Collectors.toList());
    }

    @RequestMapping(
        value = "/joined",
        method = RequestMethod.GET)
    public List<RestPrinterDto> getAllPrinterJoined() {
        return printerRepository.findAllJoined().stream().map(RestPrinterDto::from).collect(Collectors.toList());
    }

    @RequestMapping(
        value = "/joined/{id}",
        method = RequestMethod.GET)
    public RestPrinterDto getPrinterByIdJoined(@PathVariable("id") Long id) {
        return RestPrinterDto.from(printerRepository.findOneJoined(id));
    }

    @RequestMapping(
        value = "/joined/owner/{id}",
        method = RequestMethod.GET)
    public List<RestPrinterDto> getPrinterByOwnerIdJoined(@PathVariable("id") Long id) {
        return printerRepository.findByOwnerIdJoined(id).stream().map(RestPrinterDto::from).collect(Collectors.toList());
    }

    @RequestMapping(
        value = "/filtered",
        method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserFiltered(@RequestParam List<String> fields, @RequestParam Map<String, Object> allRequestParams) {

        GenericParameters parameter = GenericParameterBuilder
            .filterWith(toFilter(allRequestParams))
            .sortBy(allRequestParams.getOrDefault(GeneralFilterConstants.SORT_BY, PrinterConstants.FieldName.ID))
            .sortOrder(allRequestParams.getOrDefault(GeneralFilterConstants.SORT_ORDER, GeneralFilterConstants.ASC))
            .page(allRequestParams.getOrDefault(GeneralFilterConstants.PAGE, GeneralFilterConstants.DEFAULT_PAGE))
            .pageSize(allRequestParams.getOrDefault(GeneralFilterConstants.PAGE_SIZE, GeneralFilterConstants.DEFAULT_PAGE_SIZE))
            .withFields(fields)
            .joinTables(false)
            .build();

        return parameter.validate(PrinterConstants.ENTITY_NAME).map(printerService::find).fold(
            this::errorToResponse,
            this::toResponse
        );
    }

    @RequestMapping(
        value = "/filtered/joined",
        method = RequestMethod.GET)
    public ResponseEntity<?> getAllUserFilteredAndJoined(@RequestParam List<String> fields, @RequestParam Map<String, Object> allRequestParams) {

        GenericParameters parameter = GenericParameterBuilder
            .filterWith(toFilter(allRequestParams))
            .sortBy(allRequestParams.getOrDefault(GeneralFilterConstants.SORT_BY, PrinterConstants.FieldName.ID))
            .sortOrder(allRequestParams.getOrDefault(GeneralFilterConstants.SORT_ORDER, GeneralFilterConstants.ASC))
            .page(allRequestParams.getOrDefault(GeneralFilterConstants.PAGE, GeneralFilterConstants.DEFAULT_PAGE))
            .pageSize(allRequestParams.getOrDefault(GeneralFilterConstants.PAGE_SIZE, GeneralFilterConstants.DEFAULT_PAGE_SIZE))
            .withFields(fields)
            .joinTables(true)
            .build();

        return parameter.validate(PrinterConstants.ENTITY_NAME).map(printerService::find).fold(
            this::errorToResponse,
            this::toResponse
        );
    }

    private GenericFilter toFilter(Map<String, Object> allParams) {
        GenericFilter filter = new GenericFilter();

        filter.on(PrinterConstants.FieldName.ID)
            .withValue(allParams.get(PrinterConstants.FieldName.ID))
            .onlyIfNotNull()
            .addFilter();

        filter.on(PrinterConstants.FieldName.OWNER_ID)
            .withValue(allParams.get(PrinterConstants.FieldName.OWNER_ID))
            .onlyIfNotNull()
            .addFilter();

        return filter;
    }
}
