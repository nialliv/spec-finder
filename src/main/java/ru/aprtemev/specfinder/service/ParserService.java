package ru.aprtemev.specfinder.service;

import java.io.File;
import java.util.Map;

public interface ParserService {

    Map<String, String> getFieldsFromExcel(File file); //mb stream
}
