package ru.aprtemev.specfinder.service;

import ru.aprtemev.specfinder.dto.PrinterResponseDto;
import ru.aprtemev.specfinder.frontend.Page;
import ru.aprtemev.specfinder.frontend.PageArray;
import ru.aprtemev.specfinder.frontend.PagingRequest;

// TODO refactor and merge with original service
public interface DraftPrinterService {

    Page<PrinterResponseDto> getPrinters(PagingRequest pagingRequest);


    PageArray getPrintersArray(PagingRequest pagingRequest);
}
