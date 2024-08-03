package ru.aprtemev.specfinder.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class ProductEntity {

    @Id
    private String model;

    private Map<String, Spec> specs;

}
