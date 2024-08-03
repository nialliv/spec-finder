package ru.aprtemev.specfinder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.aprtemev.specfinder.entity.ProductEntity;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    ProductEntity findByModel(String model);
}
