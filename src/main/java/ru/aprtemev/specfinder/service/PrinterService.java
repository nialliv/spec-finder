package ru.aprtemev.specfinder.service;

import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.dto.PrinterRequestDto;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.entity.PrinterEntity;

import java.util.List;

public interface PrinterService {

    List<PrinterResponseDto> getAll();

    void insertOne(PrinterRequestDto printerRequestDto);

    void deleteAll();

    List<PrinterEntity> uploadFile(MultipartFile multipartFile);
}
