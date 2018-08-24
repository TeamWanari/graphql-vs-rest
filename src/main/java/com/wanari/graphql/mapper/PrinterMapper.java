package com.wanari.graphql.mapper;

import com.wanari.graphql.controller.dto.RestPrinterDto;
import com.wanari.graphql.domain.Printer;
import com.wanari.generic_filter.GenericFilterUtil;
import com.wanari.generic_filter.GenericMapper;
import com.wanari.graphql.domain.constants.PrinterConstants;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PrinterMapper implements GenericMapper<RestPrinterDto, Printer> {

    private final RestPrinterOwnerMapper ownerMapper;

    public PrinterMapper(RestPrinterOwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    public RestPrinterDto mapWithFields(Printer printer, List<String> fields) {
        RestPrinterDto dto = new RestPrinterDto();
        dto.id = printer.id;
        if(fields.contains(PrinterConstants.FieldName.NAME)) {
            dto.name = printer.name;
        }
        if(fields.contains(PrinterConstants.FieldName.SERIAL_NUMBER)) {
            dto.serialNumber = printer.serialNumber;
        }
        if(fields.contains(PrinterConstants.FieldName.OWNER)) {
            List<String> ownerFields = GenericFilterUtil.getNestedFields(fields, PrinterConstants.FieldName.OWNER);
            dto.owner = ownerMapper.mapWithFields(printer.owner, ownerFields);
        }
        return dto;
    }
}
