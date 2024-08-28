package ru.aprtemev.specfinder.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface PrinterService {

    void deleteAll();

    List<Map<String, String>> uploadFile(MultipartFile multipartFile);

    List<Map<String, String>> getAllPrinters();

    List<Map<String, String>> getPrintersByFilter(MultipartFile filter);
}
