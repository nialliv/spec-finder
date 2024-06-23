package ru.aprtemev.specfinder.dto;

import lombok.Data;

import java.util.Map;

@Data
public class PrinterResponseDto {

    private String model;

    private Integer printAreaX;

    private Integer printAreaY;

    private Integer printAreaZ;

    private Map<String, String> specs;
}
