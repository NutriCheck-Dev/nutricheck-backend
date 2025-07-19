package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenFoodFactsMapperTest {

    @Test
    void toDTOTest() {

        OpenFoodFactsMapper mapper = Mappers.getMapper(OpenFoodFactsMapper.class);

        List<FoodProductDTO> expectedProducts = TestDataFactory.createFoodProductsFromOpenFoodFacts();
        List<FoodProductDTO> mappedProducts = mapper.toDTO(TestDataFactory.createOpenFoodFactsFoodProducts());

        assertEquals(expectedProducts, mappedProducts);
    }
}
