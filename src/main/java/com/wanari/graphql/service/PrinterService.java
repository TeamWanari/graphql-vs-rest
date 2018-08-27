package com.wanari.graphql.service;

import com.wanari.graphql.controller.dto.RestPrinterDto;
import com.wanari.graphql.domain.Printer;
import com.wanari.graphql.domain.constants.PrinterConstants;
import com.wanari.graphql.mapper.PrinterMapper;
import com.wanari.graphql.repository.PrinterRepository;
import com.wanari.utils.spring.genericfilter.ValidGenericParameters;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrinterService {

    private final PrinterRepository printerRepository;
    private final PrinterMapper printerMapper;

    public PrinterService(PrinterRepository printerRepository, PrinterMapper printerMapper) {
        this.printerRepository = printerRepository;
        this.printerMapper = printerMapper;
    }

    public List<RestPrinterDto> find(ValidGenericParameters validParameter) {
        if(validParameter.fields.contains(PrinterConstants.Wrapper.DETAILS)) {
            validParameter.fields.addAll(PrinterConstants.details);
        }
        return printerRepository.find(validParameter, Printer.class, printerMapper::mapWithFields);
    }

}
