package ru.aprtemev.specfinder.service;

import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.entity.PrinterEntity;

import java.util.List;

public interface PrinterService {

    void deleteAll();

    List<PrinterEntity> uploadFile(MultipartFile multipartFile);

    List<PrinterEntity> getAllPrinters();

    List<PrinterEntity> getPrintersByFilter(MultipartFile filter);

    PrinterEntity getPrinter(String id);
}
