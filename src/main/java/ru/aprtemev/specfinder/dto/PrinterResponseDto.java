package ru.aprtemev.specfinder.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

// TODO mb rename in simple Printer or PrinterDTO
@Data
public class PrinterResponseDto {

    private String model;

    @JsonProperty("print_area_x")
    private Integer printAreaX;

    @JsonProperty("print_area_y")
    private Integer printAreaY;

    @JsonProperty("print_area_z")
    private Integer printAreaZ;

    private Map<String, String> specs;
}
