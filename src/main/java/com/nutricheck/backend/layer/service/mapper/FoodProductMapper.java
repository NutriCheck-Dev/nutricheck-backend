package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodProductMapper {
    FoodProductDTO toDTO(FoodProduct foodProduct);
    @Mapping(target = "references", ignore = true) // References will be set manually in the service layer
    FoodProduct toEntity(FoodProductDTO foodProductDTO);
    List<FoodProductDTO> toDTO(List<FoodProduct> foodProducts);
    List<FoodProduct> toEntity(List<FoodProductDTO> foodProductDTOs);
}
