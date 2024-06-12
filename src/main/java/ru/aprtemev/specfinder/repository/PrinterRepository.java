package ru.aprtemev.specfinder.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.aprtemev.specfinder.entity.PrinterEntity;

public interface PrinterRepository extends MongoRepository<PrinterEntity, String> {
}
