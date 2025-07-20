package com.nutricheck.backend.layer.client.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.SwissFoodCDFoodProductDTO;
import com.nutricheck.backend.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwissFoodCDMapperTest {


    @Test
    void toDTOTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        SwissFoodCDMapper mapper = Mappers.getMapper(SwissFoodCDMapper.class);

        List<FoodProductDTO> expectedProducts = List.of(
                TestDataFactory.createFoodProductDTOOneFromSwissDB(),
                TestDataFactory.createFoodProductDTOTwoFromSwissDB()
        );
        List<SwissFoodCDFoodProductDTO> productsToMap = List.of(
                objectMapper.readValue(FileUtil.readFileAsString("swiss-food-product-one-example.json"), SwissFoodCDFoodProductDTO.class),
                objectMapper.readValue(FileUtil.readFileAsString("swiss-food-product-two-example.json"), SwissFoodCDFoodProductDTO.class)
        );
        List<FoodProductDTO> mappedProducts = mapper.toDTO(productsToMap);

        assertEquals(expectedProducts, mappedProducts);
    }
}
