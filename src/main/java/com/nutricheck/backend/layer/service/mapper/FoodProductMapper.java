package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface FoodProductMapper {
    FoodProductDTO toDTO(FoodProduct foodProduct);
    FoodProduct toEntity(FoodProductDTO foodProductDTO);
    List<FoodProductDTO> toDTO(List<FoodProduct> foodProducts);
    List<FoodProduct> toEntity(List<FoodProductDTO> foodProductDTOs);
}
