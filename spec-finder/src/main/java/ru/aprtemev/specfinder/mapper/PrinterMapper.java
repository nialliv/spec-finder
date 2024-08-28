package ru.aprtemev.specfinder.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.utils.PrinterFieldsContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@RequiredArgsConstructor
public class PrinterMapper {

    private final ObjectMapper objectMapper;

    public List<Map<String, String>> mapToResponse(List<PrinterEntity> printerEntities) {
        // sorry about this shit-code, but I'm zaebalsya
        List<Map<String, String>> result = new ArrayList<>(printerEntities.size());
        for (PrinterEntity printer : printerEntities) {
            Map<String, String> translatedMap = new TreeMap<>();
            Map<String, Object> map = objectMapper.convertValue(printer, new TypeReference<>() {
            });
            map.forEach((key, value) -> {
                String translatedKey = PrinterFieldsContainer.getRusFieldByEng(key)
                        .orElse(key);
                translatedMap.put(translatedKey, String.valueOf(value));
            });
            result.add(translatedMap);
        }
        return result;
    }
}
