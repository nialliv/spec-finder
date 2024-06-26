package ru.aprtemev.specfinder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.aprtemev.specfinder.dto.Printer;
import ru.aprtemev.specfinder.dto.frontend.Column;
import ru.aprtemev.specfinder.dto.frontend.Order;
import ru.aprtemev.specfinder.dto.frontend.Page;
import ru.aprtemev.specfinder.dto.frontend.PageArray;
import ru.aprtemev.specfinder.dto.frontend.PagingRequest;
import ru.aprtemev.specfinder.entity.PrinterEntity;
import ru.aprtemev.specfinder.mapper.PrinterMapper;
import ru.aprtemev.specfinder.repository.PrinterRepository;
import ru.aprtemev.specfinder.service.DraftPrinterService;
import ru.aprtemev.specfinder.utils.PrinterComparators;
import ru.aprtemev.specfinder.utils.PrinterFilterUtils;

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

    private final PrinterRepository printerRepository;
    private final PrinterMapper mapper;
    public static final List<Column> COLUMNS =
            Stream.of(
                            "model",
                            "print_area_x",
                            "print_area_y",
                            "print_area_z",
                            "maxPrintSpeed",
                            "printHeadMovementSpeed",
                            "typeCompatiblePlastic",
                            "connectInterface",
                            "platformCalibration",
                            "numbsOfNozzlesOnPrintHead",
                            "minLayerThickness",
                            "nozzleDiameter",
                            "maxPrintHeadTemp",
                            "coolingPrintArea",
                            "availabilityOfPlasticControlSys",
                            "maximumPrintTemperature",
                            "printPlatformType",
                            "availabilityOfClosedCase",
                            "availabilityOfVentilationSystem",
                            "availabilityOfHEPAFilter",
                            "availabilityOfPrintPlatformControlSystem",
                            "typeOfFirstLayerHeightControlSystem",
                            "printFileFormat",
                            "availabilityOfBuiltSettingsFunction",
                            "printerControlType",
                            "availabilityOfAccessControlToPrinter",
                            "availabilityOfContinuePrintingAfterPowerOff",
                            "availabilityOfRemoteControl",
                            "availabilityOfDryingModeFunction",
                            "countryOfOrigin",
                            "price",
                            "filamentFeedType",
                            "filamentDiameter",
                            "material",
                            "printBedMaterial",
                            "printBedCalibrationType")
                    .map(Column::new)
                    .toList();
    private static final Comparator<Printer> EMPTY_COMPARATOR = (e1, e2) -> 0;

    @Override
    public PageArray getPrintersArray(PagingRequest pagingRequest) {
        pagingRequest.setColumns(COLUMNS);
        Page<Printer> printerPage = getPrinters(pagingRequest);

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered(printerPage.getRecordsFiltered());
        pageArray.setRecordsTotal(printerPage.getRecordsTotal());
        pageArray.setDraw(printerPage.getDraw());
        pageArray.setData(printerPage.getData()
                .stream()
                .map(Printer::toStringList)
                .toList());
        return pageArray;
    }

    @Override
    public Page<Printer> getPrinters(PagingRequest pagingRequest) {
        List<PrinterEntity> entities = printerRepository.findAll();
        List<Printer> printers = mapper.mapToDto(entities);
        return getPage(printers, pagingRequest);
    }

    private Page<Printer> getPage(List<Printer> printers, PagingRequest pagingRequest) {
        List<Printer> filtered = printers.stream()
                .filter(filterPrinters(pagingRequest))
                .sorted(sortPrinters(pagingRequest))
                .skip(pagingRequest.getStart())
                .limit(pagingRequest.getLength())
                .toList();

        long count = printers.stream()
                .filter(filterPrinters(pagingRequest))
                .count();

        Page<Printer> page = new Page<>(filtered);
        page.setRecordsFiltered((int) count);
        page.setRecordsTotal((int) count);
        page.setDraw(pagingRequest.getDraw());

        return page;
    }

    private Predicate<Printer> filterPrinters(PagingRequest pagingRequest) {
        if (pagingRequest.getSearch() == null || StringUtils.isEmpty(pagingRequest.getSearch()
                .getValue())
        ) {
            return employee -> true;
        }

        String value = pagingRequest.getSearch()
                .getValue();

        return PrinterFilterUtils.getPredicate(value);
    }

    private Comparator<Printer> sortPrinters(PagingRequest pagingRequest) {
        if (pagingRequest.getOrder() == null) {
            return EMPTY_COMPARATOR;
        }

        try {
            Order order = pagingRequest.getOrder()
                    .get(0);

            int columnIndex = order.getColumn();
            Column column = pagingRequest.getColumns()
                    .get(columnIndex);

            Comparator<Printer> comparator = PrinterComparators.getComparator(column.getData(), order.getDir());

            return Objects.requireNonNullElse(comparator, EMPTY_COMPARATOR);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EMPTY_COMPARATOR;
    }
}
