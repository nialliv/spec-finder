package ru.aprtemev.specfinder.dto.frontend;

import lombok.Data;

import java.util.List;

@Data
public class PageArray {

    private List<List<String>> data;

    private int recordsFiltered;

    private int recordsTotal;

    private int draw;

}
