package ru.aprtemev.specfinder.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class PrinterRequestDto {

    @NotBlank
    private String model;

    @NotNull
    @Min(0)
    private Integer printAreaX;

    @NotNull
    @Min(0)
    private Integer printAreaY;

    @NotNull
    @Min(0)
    private Integer printAreaZ;

    private Map<String, String> specs;
}
