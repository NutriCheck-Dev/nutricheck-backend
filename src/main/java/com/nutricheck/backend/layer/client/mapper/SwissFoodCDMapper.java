package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.SwissFoodCDFoodProductDTO;
import com.nutricheck.backend.dto.external.SwissFoodCDValueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

/**
 * Mapper interface for converting SwissFoodCD data transfer objects to FoodProductDTOs.
 * This interface uses MapStruct to automatically generate the implementation.
 */
@Mapper(componentModel = "spring")
public interface SwissFoodCDMapper {
    @Mapping(source = "values", target = "fat", qualifiedByName = "mapFatFromValues")
    @Mapping(source = "values", target = "calories", qualifiedByName = "mapCaloriesFromValues")
    @Mapping(source = "values", target = "carbohydrates", qualifiedByName = "mapCarbohydratesFromValues")
    @Mapping(source = "values", target = "protein", qualifiedByName = "mapProteinFromValues")
    FoodProductDTO toFoodProductDTO(SwissFoodCDFoodProductDTO foodProduct);

    List<FoodProductDTO> toFoodProductDTO(List<SwissFoodCDFoodProductDTO> foodProducts);

    @Named("mapFatFromValues")
    default double mapFromValues(List<SwissFoodCDValueDTO> values) {
        for (SwissFoodCDValueDTO value : values) {
            if (value.getComponent().getCode().equals("FAT")) {
                return value.getValue() != null ? value.getValue() : 0;
            }
        }
        return 0;
    }

    @Named("mapCaloriesFromValues")
    default double mapCaloriesFromValues(List<SwissFoodCDValueDTO> values) {
        for (SwissFoodCDValueDTO value : values) {
            if (value.getComponent().getCode().equals("ENERCC")) {
                return value.getValue() != null ? value.getValue() : 0;
            }
        }
        return 0;
    }

    @Named("mapCarbohydratesFromValues")
    default double mapCarbohydratesFromValues(List<SwissFoodCDValueDTO> values) {
        for (SwissFoodCDValueDTO value : values) {
            if (value.getComponent().getCode().equals("CHO")) {
                return value.getValue() != null ? value.getValue() : 0;
            }
        }
        return 0;
    }

    @Named("mapProteinFromValues")
    default double mapProteinFromValues(List<SwissFoodCDValueDTO> values) {
        for (SwissFoodCDValueDTO value : values) {
            if (value.getComponent().getCode().equals("PROT625")) {
                return value.getValue() != null ? value.getValue() : 0;
            }
        }
        return 0;
    }

}
