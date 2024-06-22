package ru.aprtemev.specfinder.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.dto.PrinterRequestDto;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.service.PrinterService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/printers")
@RequiredArgsConstructor
public class PrinterController {

    private final PrinterService printerService;

    @GetMapping("/all")
    public List<PrinterResponseDto> getAll() {
        return printerService.getAll();
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        printerService.uploadFile(multipartFile);
    }

    @PutMapping("/insert")
    public void insertOnePrinter(@RequestBody @Valid PrinterRequestDto printerRequestDto) {
        printerService.insertOne(printerRequestDto);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        printerService.deleteAll();
    }

}
