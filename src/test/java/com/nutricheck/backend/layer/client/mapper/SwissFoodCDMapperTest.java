package com.nutricheck.backend.layer.client.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.SwissFoodCDFoodProductDTO;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SwissFoodCDMapperTest {

    private ObjectMapper objectMapper;

    SwissFoodCDMapper mapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mapper = Mappers.getMapper(SwissFoodCDMapper.class);
    }

    @Test
    void toFoodProductDTOTest() throws Exception {
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

    @Test
    void toFoodProductDTOWithEmptyValuesTest() throws Exception {
        FoodProductDTO expectedProduct = TestDataFactory.createFoodProductDTOOneFromSwissDB();
        expectedProduct.setFat(0);
        expectedProduct.setCalories(0);
        expectedProduct.setCarbohydrates(0);
        expectedProduct.setProtein(0);

        ClassPathResource resource = new ClassPathResource("swiss-food-product-one-missing-fields.json");
        SwissFoodCDFoodProductDTO productToMap = objectMapper.readValue(
                FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8),
                SwissFoodCDFoodProductDTO.class);

        FoodProductDTO mappedProduct = mapper.toFoodProductDTO(productToMap);

        assertThat(mappedProduct)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedProduct);
    }
}
