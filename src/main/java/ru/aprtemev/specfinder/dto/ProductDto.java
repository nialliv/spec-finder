package ru.aprtemev.specfinder.dto;

import lombok.Data;

import java.util.Map;

@Data
public class ProductDto {

    private String model;

    private Map<String, String> specs;
}
