package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwissFoodCDMapperTest {

    @Test
    void toDTOTest() {

        SwissFoodCDMapper mapper = Mappers.getMapper(SwissFoodCDMapper.class);

        List<FoodProductDTO> expectedProducts = TestDataFactory.createFoodProductsFromSwissFoodCD();
        List<FoodProductDTO> mappedProducts = mapper.toDTO(TestDataFactory.createSwissFoodCDFoodProducts());

        assertEquals(expectedProducts, mappedProducts);
    }
}
