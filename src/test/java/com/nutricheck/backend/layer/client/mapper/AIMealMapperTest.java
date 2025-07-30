package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.MealDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class AIMealMapperTest {

    @Test
    void  toMealDTOTest() {
        AIMealMapper aiMealMapper = Mappers.getMapper(AIMealMapper.class);
        MealDTO expectedMealDTO = TestDataFactory.createDefaultMealDTO();

        MealDTO actualMealDTO = aiMealMapper.toMealDTO(TestDataFactory.createDefaultAIMealDTO());
        assertThat(actualMealDTO)
                .usingRecursiveComparison()
                // ids are generated, names have (AI) appended
                .ignoringFieldsMatchingRegexes(".*\\.id", ".*\\.name", ".*\\.foodProductId")
                .isEqualTo(expectedMealDTO);
    }
}
