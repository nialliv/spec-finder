package ru.aprtemev.specfinder.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ru.aprtemev.specfinder.dto.ProductDto;
import ru.aprtemev.specfinder.entity.ProductEntity;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    //todo write this
    List<ProductDto> map(List<ProductEntity> entities);

    ProductDto map(ProductEntity entity);

//    Map<String, String> map(Map<String, >)

}
