package ru.aprtemev.specfinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.service.PrinterService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/printers")
@RequiredArgsConstructor
public class PrinterController {

    private final PrinterService printerService;

    @GetMapping ("/all")
    public List<Map<String, String>> getAllModelsByFilter() {
            return printerService.getAllPrinters();
    }

    @PostMapping("/all")
    public List<Map<String, String>> getAllByFilter(@RequestParam(value = "filter", required = false) MultipartFile filter) {
        return printerService.getPrintersByFilter(filter);
    }

    @PostMapping("/importDocument")
    public List<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        return printerService.uploadFile(multipartFile);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        printerService.deleteAll();
    }
}
