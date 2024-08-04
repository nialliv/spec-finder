package ru.aprtemev.specfinder.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.CellType;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.stream.Stream;

@Slf4j
@Service
public class ParserServiceImpl implements ParserService {

    @Value("${excel-parser.index-of-header:1}")
    private Integer indexOfHeaderLine;
    @Value("${excel-parser.index-of-param:0}")
    private Integer indexOfParamKey;
    @Value("${excel-parser.count-left-offset:2}")
    private Integer countOfLeftOffset;

    private static final Set<String> EXCEL_TYPES = Set.of(
            "xls", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    private static final Set<String> DOC_TYPES = Set.of(
            "docx", "doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

    @Override
    public Map<Integer, List<String>> parsePrintersFile(MultipartFile file) {
        validateFile(file, EXCEL_TYPES);
        Map<Integer, List<String>> data = new HashMap<>();

        getStreamRow(file)
                .forEach(row -> addRowInData(data, row));
        return data;
    }

    @Override
    public Map<Integer, ProductEntity> parseProductsFile(MultipartFile file) {
        validateFile(file, EXCEL_TYPES);
        Map<Integer, ProductEntity> entitiesByIndexFromFile = new HashMap<>();
        getStreamRow(file)
                .forEach(row -> addEntity(entitiesByIndexFromFile, row));
        return entitiesByIndexFromFile;
    }

    @Override
    public Query parseProductFilterFile(MultipartFile file) {
        return null;
    }

    private void addEntity(Map<Integer, ProductEntity> entities, Row row) {
        // Row 1 [[STRING "хар-ки"], [STRING "параметры"], [STRING "danya"], [EMPTY null], [EMPTY null], [EMPTY null]]
        // Row 2 [[STRING "Область печати по оси X"], [STRING "< 200 мм, 200 мм, > 200 мм"], [NUMBER "180"]]
        // Row 3 [[STRING "Область печати по оси Y"], [STRING "< 200 мм, 200 мм, > 200 мм"], [NUMBER "180"]]
        // Row 4 [[STRING "Область печати по оси Z"], [STRING "< 200 мм, 200 мм, > 200 мм"], [NUMBER "180"]]
        // Row 5 [[STRING "Максимальная скорость печати, см3/ч"], [STRING "< 100, ≥ 100"], [NUMBER "100"]]
        // Row 30 [[STRING "Страна происхождения"], null, [STRING "Россия"], [EMPTY null], [EMPTY null], [EMPTY null]]
        /*
         * TODO:
         *  1 - get headline, maybe set offset
         *      a - validate this
         *      b - get models count
         *      c - set models as index row in map
         *  2 - get other lines
         *      a - validate isBlank string
         *      b - clean string
         *      c - set value with his type in the entity in his index row
         */
        if(row == null) {
            return;
        }
        int rowNum = row.getRowNum();
        if (indexOfHeaderLine.equals(rowNum)) {
            resolveHeadLineAndSetModelsName(row, entities);
            return;
        }
        resolveSpecLineAndSetModelName(row, entities);
    }

    private void resolveHeadLineAndSetModelsName(Row row, Map<Integer, ProductEntity> entities) {
        for (int i = countOfLeftOffset; i < row.getCellCount(); i++) {
            String rawValue = row.getCell(i).getRawValue();
            if (StringUtils.isNotBlank(rawValue)) {
                ProductEntity productEntity = new ProductEntity();
                productEntity.setModel(rawValue);
                entities.put(i, productEntity);
            }
        }
    }

    private void resolveSpecLineAndSetModelName(Row row, Map<Integer, ProductEntity> entities) {
        try {
            String paramName = row.getCell(indexOfParamKey).getRawValue();
            if(StringUtils.isBlank(paramName)) {
                log.warn("Null in paramName in row - [{}]", row);
                return;
            }
            for (int i = countOfLeftOffset; i < row.getCellCount(); i++) {
                Object paramValue = Optional.ofNullable(row.getCell(i))
                        .map(Cell::getRawValue)
                        .orElse(null);
                if (paramValue != null) {
                    ProductEntity productEntity = entities.getOrDefault(i, new ProductEntity());
                    Map<String, Spec> specs = Optional.ofNullable(productEntity.getSpecs())
                            .orElse(new HashMap<>());
                    //todo refactor if filter does not works
                    //todo set clean param
                    specs.put(paramName, buildSpec(row.getCell(i).getType(), paramValue));
                    productEntity.setSpecs(specs);
                }
            }
        } catch (Exception exception) {
            log.error("An error occurred while parsing the row - [{}]", row, exception);
            log.info("The row is skipped");
        }
    }

    private Spec buildSpec(CellType type, Object paramValue) {
        return switch (type) {
            case NUMBER -> new Spec(Integer.class, paramValue);
            case BOOLEAN -> new Spec(Boolean.class, paramValue);
            default -> new Spec(String.class, paramValue);
        };
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

    private Stream<Row> getStreamRow(MultipartFile file) {
        try (InputStream fileStream = file.getInputStream();
             ReadableWorkbook workbook = new ReadableWorkbook(fileStream)) {
            return workbook.getActiveSheet()
                    .orElseThrow(() -> new RuntimeException("Not found active sheet")) //todo set handler
                    .openStream();
        } catch (Exception exception) {
            log.error("Error while parse excel", exception);
            throw new RuntimeException();
        }
    }

    private void validateFile(MultipartFile file, Set<String> supportedTypes) {
        if (file.isEmpty() || !supportedTypes.contains(file.getContentType())) {
            // TODO add error handler
            log.error("File empty or not supported fileType = [{}]", file.getContentType());
            throw new RuntimeException("File empty or not supported fileType");
        }
    }
}
