package ru.aprtemev.specfinder.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.aprtemev.specfinder.dto.ProductDto;
import ru.aprtemev.specfinder.entity.ProductEntity;
import ru.aprtemev.specfinder.mapper.ProductMapper;
import ru.aprtemev.specfinder.repository.ProductRepository;
import ru.aprtemev.specfinder.service.ParserService;
import ru.aprtemev.specfinder.service.ProductService;


import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ParserService parserService;
    private final MongoTemplate mongoTemplate;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> getAll() {
        List<ProductEntity> entities = productRepository.findAll();
        return productMapper.map(entities);
    }

    @Override
    public List<String> importProductsFromFile(MultipartFile file) {
        List<ProductEntity> productEntities = parserService.parseProductsFile(file);
        productRepository.saveAll(productEntities);
        return productEntities.stream()
                .map(ProductEntity::getModel)
                .toList();
    }

    @Override
    public List<ProductDto> getAllByFileFilter(MultipartFile file) {
        Query query = parserService.parseProductFilterFile(file);
        List<ProductEntity> entities = mongoTemplate.find(query, ProductEntity.class);
        return productMapper.map(entities);
    }

    @Override
    public ProductDto getByModel(String model) {
        ProductEntity entity = productRepository.findByModel(model);
        return productMapper.map(entity);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }
}
