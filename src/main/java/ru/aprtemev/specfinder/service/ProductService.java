package ru.aprtemev.specfinder.service;

import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAll();

    List<String> importProductsFromFile(MultipartFile file);

    List<ProductDto> getAllByFileFilter(MultipartFile file);

    ProductDto getByModel(String model);

    void deleteAll();

}
