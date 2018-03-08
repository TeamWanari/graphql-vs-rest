package com.wanari.graphql.controller;


import com.wanari.graphql.controller.dto.RestPrinterDto;
import com.wanari.graphql.repository.PrinterRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/printer")
public class PrinterController {

    private final PrinterRepository printerRepository;

    public PrinterController(PrinterRepository printerRepository) {
        this.printerRepository = printerRepository;
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

}
