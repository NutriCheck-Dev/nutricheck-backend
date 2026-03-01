package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDto;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodProductMapperTest {

    private FoodProductMapper mapper;
    private List<FoodProduct> foodProducts;
    private List<FoodProductDto> foodProductDtos;


    @BeforeEach
    void setup() {
        this.mapper = Mappers.getMapper(FoodProductMapper.class);
        this.foodProducts = List.of(TestDataFactory.createDefaultFoodProduct(),
                TestDataFactory.createDefaultFoodProduct2());
        this.foodProductDtos = List.of(TestDataFactory.createDefaultFoodProductDTO(),
                TestDataFactory.createDefaultFoodProductDTO2());
    }

    @Test
    void toFoodProductDTOTest() {
        List<FoodProductDto> mappedProducts = mapper.toDTO(foodProducts);

        assertEquals(foodProductDtos, mappedProducts);
    }

    @Test
    void toFoodProductTest() {
        List<FoodProduct> mappedProducts = mapper.toEntity(foodProductDtos);

        assertEquals(foodProducts, mappedProducts);
    }
}