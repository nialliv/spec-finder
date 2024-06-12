package ru.aprtemev.specfinder.service;

import ru.aprtemev.specfinder.dto.PrinterRequestDto;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;

import java.util.List;

public interface PrinterService {

    List<PrinterResponseDto> getAll();

    void insertOne(PrinterRequestDto printerRequestDto);

    void deleteAll();

}
