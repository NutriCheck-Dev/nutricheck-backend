package com.nutricheck.backend.layer.client.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.SwissFoodCDFoodProductDTO;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;
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
        ClassPathResource resource1 = new ClassPathResource("swiss-food-product-one-example.json");
        ClassPathResource resource2 = new ClassPathResource("swiss-food-product-two-example.json");
        List<SwissFoodCDFoodProductDTO> productsToMap = List.of(
                objectMapper.readValue(FileUtils.readFileToString(resource1.getFile(), StandardCharsets.UTF_8),
                        SwissFoodCDFoodProductDTO.class),
                objectMapper.readValue(FileUtils.readFileToString(resource2.getFile(), StandardCharsets.UTF_8),
                        SwissFoodCDFoodProductDTO.class)
        );
        List<FoodProductDTO> mappedProducts = mapper.toFoodProductDTO(productsToMap);

        assertThat(mappedProducts)
                .usingRecursiveComparison()
                .ignoringFields("id") // ids are generated and therefore not relevant for comparison
                .isEqualTo(expectedProducts);
    }
}
