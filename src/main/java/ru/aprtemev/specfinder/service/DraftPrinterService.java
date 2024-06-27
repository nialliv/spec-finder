package ru.aprtemev.specfinder.service;

import ru.aprtemev.specfinder.dto.Printer;
import ru.aprtemev.specfinder.dto.frontend.Page;
import ru.aprtemev.specfinder.dto.frontend.PageArray;
import ru.aprtemev.specfinder.dto.frontend.PagingRequest;

// TODO refactor and merge with original service
public interface DraftPrinterService {

    Page<Printer> getPrinters(PagingRequest pagingRequest);


    PageArray getPrintersArray(PagingRequest pagingRequest);
}
