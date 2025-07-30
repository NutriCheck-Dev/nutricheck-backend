package com.nutricheck.backend.layer.client.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.*;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.dto.external.AIMealDTO;
import com.nutricheck.backend.layer.client.AIModelClient;
import com.nutricheck.backend.layer.client.mapper.AIMealMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class GeminiClient implements AIModelClient {
    private static final Schema RESPONSE_SCHEMA = createResponseSchema();
    private final ObjectMapper objectMapper;
    private final AIMealMapper aiMealMapper;
    private final Client apiClient;

    public GeminiClient(@Value("${gemini.api.key}") String geminiApiKey, ObjectMapper objectMapper,
                        AIMealMapper aiMealMapper) {
        this.apiClient = Client.builder()
                .apiKey(geminiApiKey)
                .build();
        this.objectMapper = objectMapper;
        this.aiMealMapper = aiMealMapper;
    }
    @Override
    public MealDTO estimateMeal(byte[] image) throws IOException {
        Content content = Content.fromParts(
                Part.fromText(createRequestPrompt()),
                Part.fromBytes(image, MediaType.IMAGE_PNG_VALUE));

        GenerateContentConfig config = GenerateContentConfig.builder()
                .candidateCount(1)
                .responseMimeType(MediaType.APPLICATION_JSON_VALUE)
                .responseSchema(RESPONSE_SCHEMA)
                .build();
        GenerateContentResponse response = apiClient
                .models.generateContent("gemini-2.5-flash", content, config);
        System.out.println("Response from Gemini API: " + response.text());
        AIMealDTO aiEstimatedMeal = objectMapper.readValue(response.text(), AIMealDTO.class);
        return aiMealMapper.toMealDTO(aiEstimatedMeal);
    }

    private String createRequestPrompt() throws IOException {
        return FileUtils.readFileToString(
                new ClassPathResource("gemini-prompt.txt").getFile(),
                StandardCharsets.UTF_8
        );
                
    }

    private static Schema createResponseSchema() {
        Map<String, Schema> properties = ImmutableMap.of(
                "name", Schema.builder()
                        .type(Type.Known.STRING)
                        .nullable(false)
                        .build(),
                "calories", Schema.builder()
                        .type(Type.Known.NUMBER)
                        .build(),
                "carbohydrates", Schema.builder()
                        .type(Type.Known.NUMBER)
                        .minimum(0.0)
                        .build(),
                "fat", Schema.builder()
                        .type(Type.Known.NUMBER)
                        .minimum(0.0)
                        .build(),
                "protein", Schema.builder()
                        .type(Type.Known.NUMBER)
                        .minimum(0.0)
                        .build()
        );
        return Schema.builder()
                .type(Type.Known.OBJECT)
                .properties(properties)
                .required("name", "calories", "carbohydrates", "fat", "protein")
                .build();
    }
}
