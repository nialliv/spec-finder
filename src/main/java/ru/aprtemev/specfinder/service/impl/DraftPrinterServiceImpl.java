package ru.aprtemev.specfinder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.frontend.Column;
import ru.aprtemev.specfinder.frontend.Order;
import ru.aprtemev.specfinder.frontend.Page;
import ru.aprtemev.specfinder.frontend.PageArray;
import ru.aprtemev.specfinder.frontend.PagingRequest;
import ru.aprtemev.specfinder.mapper.PrinterMapper;
import ru.aprtemev.specfinder.repository.PrinterRepository;
import ru.aprtemev.specfinder.service.DraftPrinterService;
import ru.aprtemev.specfinder.utils.PrinterComparators;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

// TODO analize and merge with original service
@Service
@Slf4j
@RequiredArgsConstructor
public class DraftPrinterServiceImpl implements DraftPrinterService {

    private static final Comparator<PrinterResponseDto> EMPTY_COMPARATOR = (e1, e2) -> 0;
    private final PrinterRepository printerRepository;
    private final PrinterMapper mapper;

    @Override
    public PageArray getPrintersArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(Stream.of("model", "print_area_x", "print_area_y", "print_area_z", "specs")
                .map(Column::new)
                .toList());
        Page<PrinterResponseDto> printerPage = getPrinters(pagingRequest);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(printerPage.getRecordsFiltered());
        pageArray.setRecordsTotal(printerPage.getRecordsTotal());
        pageArray.setDraw(printerPage.getDraw());
        pageArray.setData(printerPage.getData()
                .stream()
                .map(this::toStringList)
                .toList());
        return pageArray;
    }

    private List<String> toStringList(PrinterResponseDto printer) {
        return Arrays.asList(
                printer.getModel(),
                printer.getPrintAreaX().toString(),
                printer.getPrintAreaY().toString(),
                printer.getPrintAreaZ().toString(),
                printer.getSpecs().toString());
    }

    @Override
    public Page<PrinterResponseDto> getPrinters(PagingRequest pagingRequest) {
        List<PrinterEntity> entities = printerRepository.findAll();
        List<PrinterResponseDto> printers = mapper.mapToDto(entities);
        return getPage(printers, pagingRequest);
    }

    private Page<PrinterResponseDto> getPage(List<PrinterResponseDto> printers, PagingRequest pagingRequest) {
        List<PrinterResponseDto> filtered = printers.stream()
                .sorted(sortPrinters(pagingRequest))
                .filter(filterPrinters(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .toList();

        long count = printers.stream()
                .filter(filterPrinters(pagingRequest))
                .count();

        Page<PrinterResponseDto> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<PrinterResponseDto> filterPrinters(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())) {
            return employee -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return printer -> printer.getModel()
                .toLowerCase()
                .contains(value)
                || printer.getPrintAreaX()
                .toString()
                .toLowerCase()
                .contains(value)
                || printer.getPrintAreaY()
                .toString()
                .toLowerCase()
                .contains(value)
                || printer.getPrintAreaZ()
                .toString()
                .toLowerCase()
                .contains(value);
        // TODO add filter by spec field
    }

    private Comparator<PrinterResponseDto> sortPrinters(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<PrinterResponseDto> comparator = PrinterComparators.getComparator(column.getData(), order.getDir());

            return Objects.requireNonNullElse(comparator, EMPTY_COMPARATOR);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EMPTY_COMPARATOR;
    }
}
