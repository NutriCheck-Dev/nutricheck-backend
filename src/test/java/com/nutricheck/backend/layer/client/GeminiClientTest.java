package com.nutricheck.backend.layer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.genai.Client;
import com.google.genai.Models;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.MealDto;
import com.nutricheck.backend.dto.external.AiMealDto;
import com.nutricheck.backend.layer.client.impl.GeminiClient;
import com.nutricheck.backend.layer.client.mapper.AiMealMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GeminiClientTest {
    @Mock
    private Client apiClient;
    @Mock
    private Models models;
    @Mock
    private GenerateContentResponse generateContentResponse;
    @Mock
    private AiMealMapper aiMealMapper;

    private ObjectMapper objectMapper;

    private GeminiClient geminiClient;

    private byte[] image;

    private String language;

    @BeforeEach
    void setup() throws IOException {
        objectMapper = new ObjectMapper();
        image = FileUtils.readFileToByteArray(new ClassPathResource("spaghetti.jpg").getFile());
        // key is not used as apiClient is mocked but required for instantiation
        geminiClient = new GeminiClient("testKey", objectMapper, aiMealMapper);
        ReflectionTestUtils.setField(geminiClient, "apiClient", apiClient);
        ReflectionTestUtils.setField(apiClient, "models", models);
        language = "en";
    }

    @Test
    void estimateMealTest() throws IOException {
        MealDto expectedMealDto = TestDataFactory.createMealDTOFromGemini();
        String rawResponse = FileUtils.readFileToString(
                new ClassPathResource("gemini-example.json").getFile(),
                StandardCharsets.UTF_8);
        AiMealDto aiMealDTO = objectMapper.readValue(rawResponse, AiMealDto.class);

        when(models.generateContent(any(String.class), any(Content.class), any(GenerateContentConfig.class)))
                .thenReturn(generateContentResponse);
        when(generateContentResponse.text()).thenReturn(rawResponse);
        when(aiMealMapper.toMealDto(aiMealDTO)).thenReturn(expectedMealDto);

        MealDto actualMealDto = geminiClient.estimateMeal(image, language);
        assertEquals(expectedMealDto, actualMealDto);
    }

    @Test
    void estimateMealWithInvalidLanguageTest() {
        language = "fr";
        assertThrows(IllegalArgumentException.class, () -> {
            geminiClient.estimateMeal(image, language);
        });
    }
}
