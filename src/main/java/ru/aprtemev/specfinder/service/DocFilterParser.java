package ru.aprtemev.specfinder.service;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.multipart.MultipartFile;

public interface DocFilterParser {

    Query parseDocToQueryFilter(MultipartFile file);

}
