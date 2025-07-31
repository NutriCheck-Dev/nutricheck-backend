package com.nutricheck.backend.layer.client.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.OpenFoodFactsFoodProductDTO;
import com.nutricheck.backend.dto.external.OpenFoodFactsResponseDTO;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class OpenFoodFactsMapperTest {

    @Test
    void toFoodProductDTOTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        OpenFoodFactsMapper mapper = Mappers.getMapper(OpenFoodFactsMapper.class);

        List<FoodProductDTO> expectedProducts = List.of(
                TestDataFactory.createFoodProductDTOOneFromOpenFoodFacts(),
                TestDataFactory.createFoodProductDTOTwoFromOpenFoodFacts()
        );
        ClassPathResource resource = new ClassPathResource("open-food-facts-example.json");
        List<OpenFoodFactsFoodProductDTO> productsToMap = objectMapper.readValue(
                FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8),
                OpenFoodFactsResponseDTO.class).getProducts();

        List<FoodProductDTO> mappedProducts = mapper.toFoodProductDTO(productsToMap);

        assertThat(mappedProducts)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("name") // will be set after mapping
                .containsExactlyElementsOf(expectedProducts);
    }
}
