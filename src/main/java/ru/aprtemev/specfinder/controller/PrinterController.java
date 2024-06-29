package ru.aprtemev.specfinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.dto.Printer;
import ru.aprtemev.specfinder.dto.frontend.PageArray;
import ru.aprtemev.specfinder.dto.frontend.PagingRequest;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.service.DraftPrinterService;
import ru.aprtemev.specfinder.service.PrinterService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/printers")
@RequiredArgsConstructor
public class PrinterController {

    private final PrinterService printerService;
    private final DraftPrinterService draftPrinterService; // TODO remove or change

    @GetMapping("/all")
    public List<Printer> getAll() {
        return printerService.getAll();
    }

    // TODO remove when not needed
    // TODO or maybe change....
    @PostMapping("/array")
    public PageArray array(@RequestBody PagingRequest pagingRequest) {
        return draftPrinterService.getPrintersArray(pagingRequest);
    }

    @PostMapping("/upload")
    public List<PrinterEntity> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        return printerService.uploadFile(multipartFile);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        printerService.deleteAll();
    }
}
