package ru.aprtemev.specfinder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.dto.PrinterRequestDto;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.frontend.Page;
import ru.aprtemev.specfinder.frontend.PageArray;
import ru.aprtemev.specfinder.frontend.PagingRequest;
import ru.aprtemev.specfinder.mapper.PrinterMapper;
import ru.aprtemev.specfinder.repository.PrinterRepository;
import ru.aprtemev.specfinder.service.PrinterService;
import ru.aprtemev.specfinder.utils.ExcelParser;

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
    private final PrinterMapper printerMapper;

    // TODO remove all logic in ImportExportService
    private static final Set<String> SUPPORTED_CONTENT_TYPES = Set.of("xls", "xlsx");
    private static final Map<String, BiConsumer<PrinterEntity, String>> REQUIRED_PARAMS_CONSUMERS = Map.of(
            "Область печати по оси X", (printerEntity, paramValue) -> printerEntity.setPrintAreaX(Integer.valueOf(paramValue)),
            "Область печати по оси Y", (printerEntity, paramValue) -> printerEntity.setPrintAreaY(Integer.valueOf(paramValue)),
            "Область печати по оси Z", (printerEntity, paramValue) -> printerEntity.setPrintAreaZ(Integer.valueOf(paramValue))
    );

    @Value("${excel-parser.index-of-header:1}")
    private int indexOfHeader;

    @Value("${excel-parser.index-of-param:0}")
    private int indexOfParam;

    @Value("${excel-parser.count-left-offset:2}")
    private int countLeftOffset;

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
    public List<PrinterEntity> uploadFile(MultipartFile file) {
        if (file.isEmpty() || SUPPORTED_CONTENT_TYPES.contains(file.getContentType())) {
            // TODO add error handler
            log.error("File empty or not supported fileType = [{}]", file.getContentType());
            return Collections.emptyList();
        }
        Map<Integer, List<String>> excelData = ExcelParser.readExcel(file);
        List<PrinterEntity> printerEntities = List.of(convertToEntities(excelData));
        printerRepository.saveAll(printerEntities);
        return printerEntities;
    }

    private PrinterEntity[] convertToEntities(Map<Integer, List<String>> data) {
        List<String> headerLines = data.get(indexOfHeader);
        int countModels = headerLines.size() - countLeftOffset;
        PrinterEntity[] printerEntities = new PrinterEntity[countModels];

        for (int i = 2; i < headerLines.size(); i++) {
            PrinterEntity printerEntity = new PrinterEntity();
            printerEntity.setModel(headerLines.get(i));
            printerEntities[i - 2] = printerEntity;
        }

        for (int i = 2; i < data.size(); i++) {
            List<String> line = data.get(i);
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
        if (REQUIRED_PARAMS_CONSUMERS.containsKey(param)) {
            fillParams(printerEntities, line, REQUIRED_PARAMS_CONSUMERS.get(param));
        } else {
            fillParams(printerEntities, line, getConsumerForAddOtherParams(param));
        }

    }

    private void fillParams(PrinterEntity[] printerEntities, List<String> line, BiConsumer<PrinterEntity, String> consumer) {
        for (int i = countLeftOffset; i < line.size(); i++) {
            String value = Optional.ofNullable(line.get(i))
                    .orElse(StringUtils.EMPTY);
            consumer.accept(printerEntities[i - countLeftOffset], value);
        }
    }

    private BiConsumer<PrinterEntity, String> getConsumerForAddOtherParams(String param) {
        return (printerEntity, paramValue) -> {
            Map<String, String> specMap = Optional.ofNullable(printerEntity.getSpecs())
                    .orElse(new HashMap<>());
            specMap.put(param, paramValue);
            printerEntity.setSpecs(specMap);
        };
    }
}
