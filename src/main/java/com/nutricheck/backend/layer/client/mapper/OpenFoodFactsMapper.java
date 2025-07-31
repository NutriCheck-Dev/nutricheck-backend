package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.OpenFoodFactsFoodProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper interface for converting OpenFoodFacts data transfer objects to FoodProductDTOs.
 * This interface uses MapStruct to automatically generate the implementation.
 */
@Mapper(componentModel = "spring")
public interface OpenFoodFactsMapper {
    @Mapping(target = "name", ignore = true) // will be set manually in client
    @Mapping(source = "nutriments.fat", target = "fat")
    @Mapping(source = "nutriments.energyKcal", target = "calories")
    @Mapping(source = "nutriments.carbohydrates", target = "carbohydrates")
    @Mapping(source = "nutriments.proteins", target = "protein")
    FoodProductDTO toFoodProductDTO(OpenFoodFactsFoodProductDTO foodProduct);

    List<FoodProductDTO> toFoodProductDTO(List<OpenFoodFactsFoodProductDTO> foodProducts);
}
