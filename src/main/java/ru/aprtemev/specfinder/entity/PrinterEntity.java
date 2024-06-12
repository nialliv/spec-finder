package ru.aprtemev.specfinder.entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class PrinterEntity {

    @Id
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

