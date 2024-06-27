package ru.aprtemev.specfinder.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import ru.aprtemev.specfinder.dto.Printer;
import ru.aprtemev.specfinder.entity.PrinterEntity;

@Mapper
public interface PrinterMapper {

    List<Printer> mapToDto(List<PrinterEntity> allPrinters);

    Printer mapToDto(PrinterEntity allPrinters);
}
