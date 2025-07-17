package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.OpenFoodFactsFoodProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OpenFoodFactsFoodProductMapper {
    @Mapping(source = "nutriments.fat", target = "fat")
    @Mapping(source = "nutriments.energyKcal", target = "calories")
    @Mapping(source = "nutriments.carbohydrates", target = "carbohydrates")
    @Mapping(source = "nutriments.proteins", target = "protein")
    FoodProductDTO toDTO(OpenFoodFactsFoodProductDTO foodProduct);

    List<FoodProductDTO> toDTO(List<OpenFoodFactsFoodProductDTO> foodProducts);
}
