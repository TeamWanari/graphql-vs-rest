package com.wanari.graphql.selective_rest.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wanari.graphql.domain.Printer;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectivePrinterDto {
    public Long id;
    public String name;
    public String serialNumber;

    public static SelectivePrinterDto from(Printer printer) {
        SelectivePrinterDto dto = new SelectivePrinterDto();
        dto.id = printer.id;
        dto.name = printer.name;
        dto.serialNumber = printer.serialNumber;
        return dto;
    }
}
