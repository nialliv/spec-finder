package ru.aprtemev.specfinder.dto;

import lombok.Data;
import ru.aprtemev.specfinder.entity.Spec;

import java.util.Map;

@Data
public class ProductDto {

    private String model;

    private Map<String, Spec> specs;
}
