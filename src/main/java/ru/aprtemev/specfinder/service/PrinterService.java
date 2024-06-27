package ru.aprtemev.specfinder.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ru.aprtemev.specfinder.dto.Printer;
import ru.aprtemev.specfinder.entity.PrinterEntity;

public interface PrinterService {

    List<Printer> getAll();

    void deleteAll();

    List<PrinterEntity> uploadFile(MultipartFile multipartFile);
}
