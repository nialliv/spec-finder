package ru.aprtemev.specfinder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Type;

@Data
@AllArgsConstructor
public class Spec {

    private Type type;

    private Object value;

}
