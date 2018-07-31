package com.wanari.graphql.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wanari.graphql.domain.Printer;

@JsonInclude(Include.NON_NULL)
public class RestPrinterDto {
    public Long id;
    public String name;
    public String serialNumber;
    public RestPrinterOwnerDto owner;

    public static RestPrinterDto from(Printer printer) {
        RestPrinterDto dto = new RestPrinterDto();
        dto.id = printer.id;
        dto.name = printer.name;
        dto.serialNumber = printer.serialNumber;
        dto.owner = RestPrinterOwnerDto.from(printer.owner);
        return dto;
    }
}
