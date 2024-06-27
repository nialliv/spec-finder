package ru.aprtemev.specfinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.dto.Printer;
import ru.aprtemev.specfinder.dto.frontend.Page;
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
    @PostMapping
    public Page<Printer> list(@RequestBody PagingRequest pagingRequest) {
        return draftPrinterService.getPrinters(pagingRequest);
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
