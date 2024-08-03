package ru.aprtemev.specfinder.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.CellType;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.entity.ProductEntity;
import ru.aprtemev.specfinder.entity.Spec;
import ru.aprtemev.specfinder.service.ParserService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class ParserServiceImpl implements ParserService {

    private static final Set<String> EXCEL_TYPES = Set.of(
            "xls", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    private static final Set<String> DOC_TYPES = Set.of(
            "docx", "doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

    @Override
    public Map<Integer, List<String>> parsePrintersFile(MultipartFile file) {
        validateFile(file, EXCEL_TYPES);
        Map<Integer, List<String>> data = new HashMap<>();

        try (InputStream fileStream = file.getInputStream();
             ReadableWorkbook workbook = new ReadableWorkbook(fileStream)) {
            workbook.getActiveSheet()
                    .orElseThrow(() -> new RuntimeException("Not found active sheet")) //todo set handler
                    .openStream()
                    .forEach(row -> addRowInData(data, row));
            return data;
        } catch (Exception ex) {
            log.error("Error while parse excel", ex);
            throw new RuntimeException();
        }
    }

    @Override
    public List<ProductEntity> parseProductsFile(MultipartFile file) {
        validateFile(file, EXCEL_TYPES);
        List<ProductEntity> entities = new ArrayList<>();
        try (InputStream fileStream = file.getInputStream();
             ReadableWorkbook workbook = new ReadableWorkbook(fileStream)) {
            workbook.getActiveSheet()
                    .orElseThrow(() -> new RuntimeException("Not found active sheet")) //todo set handler
                    .openStream()
                    .forEach(row -> addEntity(entities, row));
            return entities;
        } catch (Exception ex) {
            log.error("Error while parse excel", ex);
            throw new RuntimeException();
        }
    }

    @Override
    public Query parseProductFilterFile(MultipartFile file) {
        return null;
    }

    private void validateFile(MultipartFile file, Set<String> supportedTypes) {
        if (file.isEmpty() || !supportedTypes.contains(file.getContentType())) {
            // TODO add error handler
            log.error("File empty or not supported fileType = [{}]", file.getContentType());
            throw new RuntimeException("File empty or not supported fileType");
        }
    }

    private void addRowInData(Map<Integer, List<String>> data, Row row) {
        List<String> cellsData = data.computeIfAbsent(row.getRowNum(), k -> new ArrayList<>(row.getCellCount()));
        for (Cell cell : row) {
            String cellData = Optional.ofNullable(cell)
                    .map(Cell::getRawValue)
                    .orElse(null);
            cellsData.add(cellData);
        }
    }

    private void addEntity(List<ProductEntity> entities, Row row) {
        CellType type = row.getCell(0).getType();
        ProductEntity productEntity = new ProductEntity();
    }
}
