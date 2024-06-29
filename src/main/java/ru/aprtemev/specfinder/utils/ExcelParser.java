package ru.aprtemev.specfinder.utils;

import lombok.extern.slf4j.Slf4j;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class ExcelParser {

    private ExcelParser() {
    }

    public static Map<Integer, List<String>> readExcel(MultipartFile file) {
        Map<Integer, List<String>> data = new HashMap<>();

        try (InputStream fileStream = file.getInputStream();
             ReadableWorkbook workbook = new ReadableWorkbook(fileStream)) {
            workbook
                    .getSheet(2)
                    .orElseThrow(() -> new RuntimeException("Sheet with index 2 does not exists"))
                    .openStream()
                    .forEach(
                            r -> {
                                List<String> cellsData = data.computeIfAbsent(r.getRowNum(), k -> new ArrayList<>(r.getCellCount()));
                                for (Cell cell : r) {
                                    String cellData = Optional.ofNullable(cell)
                                            .map(Cell::getRawValue)
                                            .orElse(null);
                                    cellsData.add(cellData);
                                }
                            });

        } catch (Exception ex) {
            log.error("Error while parse excel", ex);
            throw new RuntimeException();
        }

        return data;
    }
}
