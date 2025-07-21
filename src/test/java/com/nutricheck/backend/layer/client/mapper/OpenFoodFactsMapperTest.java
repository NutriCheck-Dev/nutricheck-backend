package com.nutricheck.backend.layer.client.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.OpenFoodFactsFoodProductDTO;
import com.nutricheck.backend.dto.external.OpenFoodFactsResponseDTO;
import com.nutricheck.backend.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenFoodFactsMapperTest {

    @Test
    void toFoodProductDTOTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        OpenFoodFactsMapper mapper = Mappers.getMapper(OpenFoodFactsMapper.class);

        List<FoodProductDTO> expectedProducts = List.of(
                TestDataFactory.createFoodProductDTOOneFromOpenFoodFacts(),
                TestDataFactory.createFoodProductDTOTwoFromOpenFoodFacts()
        );
        List<OpenFoodFactsFoodProductDTO> productsToMap = objectMapper.readValue(
                FileUtil.readFileAsString("open-food-facts-example.json"),
                OpenFoodFactsResponseDTO.class).getProducts();

        List<FoodProductDTO> mappedProducts = mapper.toFoodProductDTO(productsToMap);

        assertEquals(expectedProducts, mappedProducts);
    }
}
