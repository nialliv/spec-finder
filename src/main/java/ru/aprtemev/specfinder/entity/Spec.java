package ru.aprtemev.specfinder.entity;

import lombok.Data;

import java.lang.reflect.Type;

@Data
public class Spec {

    private Type type;

    private Object value;

}
