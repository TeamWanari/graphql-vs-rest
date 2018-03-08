package com.wanari.graphql.controller.dto;

import com.wanari.graphql.domain.Printer;

public class RestPrinterDto {
    public Long id;
    public String name;
    public String serialNumber;

    public static RestPrinterDto from(Printer printer) {
        RestPrinterDto dto = new RestPrinterDto();
        dto.id = printer.id;
        dto.name = printer.name;
        dto.serialNumber = printer.serialNumber;
        return dto;
    }
}
