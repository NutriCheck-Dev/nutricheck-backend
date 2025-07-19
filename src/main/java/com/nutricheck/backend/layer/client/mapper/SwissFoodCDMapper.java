package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.SwissFoodCDFoodProductDTO;
import com.nutricheck.backend.dto.SwissFoodCDValueDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SwissFoodCDMapper {
    @Mapping(source = "values", target = "fat", qualifiedByName = "mapFatFromValues")
    @Mapping(source = "values", target = "calories", qualifiedByName = "mapCaloriesFromValues")
    @Mapping(source = "values", target = "carbohydrates", qualifiedByName = "mapCarbohydratesFromValues")
    @Mapping(source = "values", target = "protein", qualifiedByName = "mapProteinFromValues")
    FoodProductDTO toDTO(SwissFoodCDFoodProductDTO foodProduct);

    List<FoodProductDTO> toDTO(List<SwissFoodCDFoodProductDTO> foodProducts);

    @Named("mapFatFromValues")
    default double mapFromValues(List<SwissFoodCDValueDTO> values) {
        for (SwissFoodCDValueDTO value : values) {
            if (value.getComponent().getName().equals("Fat, total")) {
                return value.getValue();
            }
        }
        return 0;
    }

    @Named("mapCaloriesFromValues")
    default double mapCaloriesFromValues(List<SwissFoodCDValueDTO> values) {
        for (SwissFoodCDValueDTO value : values) {
            if (value.getComponent().getName().equals("Energy, kilocalories")) {
                return value.getValue();
            }
        }
        return 0;
    }

    @Named("mapCarbohydratesFromValues")
    default double mapCarbohydratesFromValues(List<SwissFoodCDValueDTO> values) {
        for (SwissFoodCDValueDTO value : values) {
            if (value.getComponent().getName().equals("Carbohydrates, available")) {
                return value.getValue();
            }
        }
        return 0;
    }

    @Named("mapProteinFromValues")
    default double mapProteinFromValues(List<SwissFoodCDValueDTO> values) {
        for (SwissFoodCDValueDTO value : values) {
            if (value.getComponent().getName().equals("Protein")) {
                return value.getValue();
            }
        }
        return 0;
    }

}
