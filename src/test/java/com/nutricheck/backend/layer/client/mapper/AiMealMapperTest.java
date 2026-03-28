package com.nutricheck.backend.layer.client.mapper;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.MealDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class AiMealMapperTest {

    @Test
    void toMealDtoTest() {
        AiMealMapper aiMealMapper = Mappers.getMapper(AiMealMapper.class);
        MealDto expectedMealDto = TestDataFactory.createDefaultMealDTO();

        MealDto actualMealDto = aiMealMapper.toMealDto(TestDataFactory.createDefaultAIMealDTO());
        assertThat(actualMealDto)
                .usingRecursiveComparison()
                // ids are generated, names have (AI) appended
                .ignoringFieldsMatchingRegexes(".*\\.id", ".*\\.name", ".*\\.foodProductId")
                .isEqualTo(expectedMealDto);
    }
}
