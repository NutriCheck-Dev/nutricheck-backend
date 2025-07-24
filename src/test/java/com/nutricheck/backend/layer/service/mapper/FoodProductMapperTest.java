package com.nutricheck.backend.layer.service.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.layer.model.entity.FoodProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FoodProductMapperTest {

    private FoodProductMapper mapper;
    private List<FoodProduct> foodProducts;
    private List<FoodProductDTO> foodProductDTOs;


    @BeforeEach
    void setUp() {
        this.mapper = Mappers.getMapper(FoodProductMapper.class);
        this.foodProducts = List.of(TestDataFactory.createDefaultFoodProduct(),
                TestDataFactory.createDefaultFoodProduct());
        this.foodProductDTOs = List.of(TestDataFactory.createDefaultFoodProductDTO(),
                TestDataFactory.createDefaultFoodProductDTO());
    }

    @Test
    void toFoodProductDTOTest() {
        List<FoodProductDTO> mappedProducts = mapper.toDTO(foodProducts);

        assertEquals(foodProductDTOs, mappedProducts);
    }

    @Test
    void toFoodProductTest() {
        List<FoodProduct> mappedProducts = mapper.toEntity(foodProductDTOs);

        for (int i = 0; i < mappedProducts.size(); i++) {
            assertEquals(foodProducts.get(i).getId(), mappedProducts.get(i).getId());
        }
    }
}