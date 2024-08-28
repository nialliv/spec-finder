package ru.aprtemev.specfinder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.mapper.PrinterMapper;
import ru.aprtemev.specfinder.repository.PrinterRepository;
import ru.aprtemev.specfinder.service.DocFilterParser;
import ru.aprtemev.specfinder.service.PrinterService;
import ru.aprtemev.specfinder.utils.ExcelParser;
import ru.aprtemev.specfinder.utils.PrinterFieldsContainer;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrinterServiceImpl implements PrinterService {

    private final PrinterRepository printerRepository;
    private final MongoTemplate mongoTemplate;
    private final DocFilterParser docFilterParser;
    private final PrinterMapper printerMapper;

    // TODO remove all logic in ImportExportService
    private static final Set<String> SUPPORTED_EXCEL_TYPES = Set.of("xls", "xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    private static final Set<String> SUPPORTED_DOC_TYPES = Set.of("docx", "doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

    @Value("${excel-parser.index-of-header:1}")
    private int indexOfHeader;

    @Value("${excel-parser.index-of-param:0}")
    private int indexOfParam;

    @Value("${excel-parser.count-left-offset:2}")
    private int countLeftOffset;

    @Override
    public void deleteAll() {
        printerRepository.deleteAll();
    }

    @Override
    public List<Map<String, String>> uploadFile(MultipartFile file) {
        if (file.isEmpty() || !SUPPORTED_EXCEL_TYPES.contains(file.getContentType())) {
            // TODO add error handler
            log.error("File empty or not supported fileType = [{}]", file.getContentType());
            return Collections.emptyList();
        }
        Map<Integer, List<String>> excelData = ExcelParser.readExcel(file);
        List<PrinterEntity> printerEntities = List.of(convertToEntities(excelData));
        printerRepository.saveAll(printerEntities);
        return printerMapper.mapToResponse(printerEntities);
    }

    @Override
    public List<Map<String, String>> getAllPrinters() {
        List<PrinterEntity> all = printerRepository.findAll();
        return printerMapper.mapToResponse(all);
    }

    @Override
    public List<Map<String, String>> getPrintersByFilter(MultipartFile file) {
        if (file.isEmpty() || !SUPPORTED_DOC_TYPES.contains(file.getContentType())) {
            log.error("File empty or not supported fileType = [{}]", file.getContentType());
            return Collections.emptyList();
        }
        Query query = docFilterParser.parseDocToQueryFilter(file);
        List<PrinterEntity> entities = mongoTemplate.find(query, PrinterEntity.class);
        return printerMapper.mapToResponse(entities);
    }

    private PrinterEntity[] convertToEntities(Map<Integer, List<String>> data) {
        List<String> headerLines = data.get(indexOfHeader).stream()
                .filter(StringUtils::isNotBlank)
                .toList();
        int countModels = headerLines.size() - countLeftOffset;
        PrinterEntity[] printerEntities = new PrinterEntity[countModels];
        for (int i = countLeftOffset; i < headerLines.size(); i++) {
            PrinterEntity printerEntity = new PrinterEntity();
            printerEntity.setModel(headerLines.get(i));
            printerEntities[i - countLeftOffset] = printerEntity;
        }

        for (int i = indexOfHeader + 1; i < data.size(); i++) {
            List<String> line = data.get(i);
            log.info("Parsing index - [{}], line - [{}]", i, line);
            if (line != null && !line.isEmpty()) {
                resolveRequireParams(printerEntities, line);
            }
        }
        return printerEntities;
    }

    private void resolveRequireParams(PrinterEntity[] printerEntities, List<String> line) {
        String param = line.get(indexOfParam);
        if (StringUtils.isBlank(param)) {
            return;
        }

        PrinterFieldsContainer container = PrinterFieldsContainer.getContainerByFieldName(param);
        if (container != null) {
            fillParams(printerEntities, line, container.getFieldSetterConsumer());
        } else {
            fillParams(printerEntities, line, getConsumerForAddOtherParams(param));
        }

    }

    private void fillParams(PrinterEntity[] printerEntities, List<String> line, BiConsumer<PrinterEntity, String> consumer) {
        if (countLeftOffset >= line.size()) {
            for (PrinterEntity printer : printerEntities) {
                consumer.accept(printer, StringUtils.EMPTY);
            }
            return;
        }
        for (int i = countLeftOffset; i < line.size() && i < printerEntities.length + countLeftOffset; i++) {
            String value = Optional.ofNullable(line.get(i))
                    .orElse(StringUtils.EMPTY);
            consumer.accept(printerEntities[i - countLeftOffset], value);
        }
    }

    private BiConsumer<PrinterEntity, String> getConsumerForAddOtherParams(String param) {
        return (printerEntity, paramValue) -> {
            Map<String, String> specMap = Optional.ofNullable(printerEntity.getOtherSpecs())
                    .orElse(new HashMap<>());
            specMap.put(param, paramValue);
            printerEntity.setOtherSpecs(specMap);
        };
    }
}
