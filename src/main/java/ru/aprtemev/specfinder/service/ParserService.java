package ru.aprtemev.specfinder.service;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.entity.ProductEntity;

import java.util.List;
import java.util.Map;

public interface ParserService {

    Map<Integer, List<String>> parsePrintersFile(MultipartFile file);

    List<ProductEntity> parseProductsFile(MultipartFile file);

    Query parseProductFilterFile(MultipartFile file);

}
