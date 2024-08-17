package ru.aprtemev.specfinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.service.PrinterService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/printers")
@RequiredArgsConstructor
public class PrinterController {

    private final PrinterService printerService;

    @GetMapping ("/all")
    public List<PrinterEntity> getAllModelsByFilter() {
            return printerService.getAllPrinters();
    }
    // todo сука поменяй все на нормальный дто с маппером
    @PostMapping("/all")
    public List<PrinterEntity> getAllByFilter(@RequestParam(value = "filter", required = false) MultipartFile filter) {
        return printerService.getPrintersByFilter(filter);
    }

    @PostMapping("/importDocument")
    public List<PrinterEntity> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        return printerService.uploadFile(multipartFile);
    }

    @GetMapping("/get/{model}")
    public PrinterEntity getByModel(@PathVariable String model) {
        return printerService.getPrinter(model);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        printerService.deleteAll();
    }
}
