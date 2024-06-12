package ru.aprtemev.specfinder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.aprtemev.specfinder.dto.PrinterRequestDto;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.mapper.PrinterMapper;
import ru.aprtemev.specfinder.repository.PrinterRepository;
import ru.aprtemev.specfinder.service.PrinterService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrinterServiceImpl implements PrinterService {

    private final PrinterRepository printerRepository;
    private final PrinterMapper printerMapper;

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
}
