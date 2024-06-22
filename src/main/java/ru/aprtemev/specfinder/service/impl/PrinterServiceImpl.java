package ru.aprtemev.specfinder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.jexl3.internal.introspection.ArrayListWrapper;
import org.apache.poi.hpsf.Array;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jxl.Workbook;
import ru.aprtemev.specfinder.dto.PrinterRequestDto;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.mapper.PrinterMapper;
import ru.aprtemev.specfinder.repository.PrinterRepository;
import ru.aprtemev.specfinder.service.PrinterService;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrinterServiceImpl implements PrinterService {

    private final PrinterRepository printerRepository;
    private final PrinterMapper printerMapper;

    private static final Set<String> supportedContentTypes = Set.of(
        "xls", "xlsx"
    );

    @Override
    public List<PrinterResponseDto> getAll() {
        List<PrinterEntity> allPrinters = printerRepository.findAll();
        log.info("Saved printers - [{}]", allPrinters);
        return printerMapper.mapToDto(allPrinters);
    }

    @Override
    public void insertOne(PrinterRequestDto printerRequestDto) {
        PrinterEntity printerEntity = printerMapper.mapToEntity(printerRequestDto);
        PrinterEntity savedEntity = printerRepository.save(printerEntity);
        log.info("Entity was saved - [{}]", savedEntity);
    }

    @Override
    public void deleteAll() {
        printerRepository.deleteAll();
    }

    @Override
    public void uploadFile(MultipartFile file) {
        if (file.isEmpty() || supportedContentTypes.contains(file.getContentType()) ) {
            log.error("File empty or not supported fileType = [{}]", file.getContentType());
            throw new RuntimeException("File empty or not supported fileType");
        }

        try {
            List<Double> result = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet sheet = workbook.getSheetAt(4);
            for(Row row : sheet) {
                log.info("row = [{}]", row);
                for(Cell cell : row) {
                    result.add(cell.getNumericCellValue());
                }
            }
            log.info("result = [{}]", result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
