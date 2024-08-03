package ru.aprtemev.specfinder.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.dto.ProductDto;
import ru.aprtemev.specfinder.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @PostMapping("/all/byFilter")
    public List<ProductDto> getAllByFileFilter(@RequestParam(value = "filter") MultipartFile file) {
        return productService.getAllByFileFilter(file);
    }

    @PostMapping("/importProducts")
    public List<String> importProductsFromFile(@RequestParam(value = "file") MultipartFile file) {
        return productService.importProductsFromFile(file);
    }

    @GetMapping("/get/{model}")
    public ProductDto getByModel(@PathVariable String model) {
        return productService.getByModel(model);
    }

    @DeleteMapping("/deleteAll")
    public void deleteAll() {
        productService.deleteAll();
    }
}
