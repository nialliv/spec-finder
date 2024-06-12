package ru.aprtemev.specfinder.mapper;

import org.mapstruct.Mapper;
import ru.aprtemev.specfinder.dto.PrinterRequestDto;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.entity.PrinterEntity;

import java.util.List;

@Mapper
public interface PrinterMapper {

    List<PrinterResponseDto> mapToDto(List<PrinterEntity> allPrinters);

    PrinterResponseDto mapToDto(PrinterEntity allPrinters);

    PrinterEntity mapToEntity(PrinterRequestDto printerRequestDto);
}
