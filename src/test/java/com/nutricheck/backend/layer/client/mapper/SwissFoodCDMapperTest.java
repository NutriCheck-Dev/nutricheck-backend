package com.nutricheck.backend.layer.client.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.SwissFoodCDFoodProductDTO;
import com.nutricheck.backend.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SwissFoodCDMapperTest {


    @Test
    void toFoodProductDTOTest() throws Exception {
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
        List<FoodProductDTO> mappedProducts = mapper.toFoodProductDTO(productsToMap);

        assertThat(mappedProducts)
                .usingRecursiveComparison()
                .ignoringFields("id") // ids are generated and therefore not relevant for comparison
                .isEqualTo(expectedProducts);
    }
}
