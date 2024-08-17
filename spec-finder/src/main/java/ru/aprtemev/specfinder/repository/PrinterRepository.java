package ru.aprtemev.specfinder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.aprtemev.specfinder.entity.PrinterEntity;

@Repository
public interface PrinterRepository extends MongoRepository<PrinterEntity, String> {
}
