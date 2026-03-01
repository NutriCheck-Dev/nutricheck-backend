package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.FoodProductDto;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper interface for converting between FoodProduct entities and FoodProductDTOs.
 * Utilizes MapStruct for automatic mapping generation.
 */
@Mapper(componentModel = "spring")
public interface FoodProductMapper {
    FoodProductDto toDTO(FoodProduct foodProduct);
    FoodProduct toEntity(FoodProductDto foodProductDTO);
    List<FoodProductDto> toDTO(List<FoodProduct> foodProducts);
    List<FoodProduct> toEntity(List<FoodProductDto> foodProductDtos);
}
