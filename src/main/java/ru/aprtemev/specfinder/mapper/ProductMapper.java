package ru.aprtemev.specfinder.mapper;

import org.mapstruct.Mapper;
import ru.aprtemev.specfinder.dto.ProductDto;
import ru.aprtemev.specfinder.entity.ProductEntity;
import ru.aprtemev.specfinder.entity.Spec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

    List<ProductDto> map(List<ProductEntity> entities);

    ProductDto map(ProductEntity entity);

    default Map<String, String> map(Map<String, Spec> source) {
        Map<String, String> target = new HashMap<>();
        for (Map.Entry<String, Spec> entry : source.entrySet()) {
            target.put(entry.getKey(), entry.getValue().getValue().toString());
        }
        return target;
    }

}
