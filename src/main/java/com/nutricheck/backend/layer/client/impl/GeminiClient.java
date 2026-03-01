package com.nutricheck.backend.layer.client.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.genai.Client;
import com.google.genai.types.*;
import com.nutricheck.backend.dto.MealDto;
import com.nutricheck.backend.dto.external.AiMealDto;
import com.nutricheck.backend.layer.client.AIModelClient;
import com.nutricheck.backend.layer.client.mapper.AiMealMapper;
import org.apache.commons.io.IOUtils;
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
    private final AiMealMapper aiMealMapper;
    private final Client apiClient;

    public GeminiClient(@Value("${gemini.api.key}") String geminiApiKey, ObjectMapper objectMapper,
                        AiMealMapper aiMealMapper) {
        this.apiClient = Client.builder()
                .apiKey(geminiApiKey)
                .build();
        this.objectMapper = objectMapper;
        this.aiMealMapper = aiMealMapper;
    }
    @Override
    public MealDto estimateMeal(byte[] image, String language) throws IOException {
        Content content = Content.fromParts(
                Part.fromText(createRequestPrompt(language)),
                Part.fromBytes(image, MediaType.IMAGE_JPEG_VALUE));

        GenerateContentConfig config = GenerateContentConfig.builder()
                .candidateCount(1)
                .responseMimeType(MediaType.APPLICATION_JSON_VALUE)
                .responseSchema(RESPONSE_SCHEMA)
                .build();
        GenerateContentResponse response = apiClient
                .models.generateContent("gemini-2.5-flash", content, config);
        AiMealDto aiEstimatedMeal = objectMapper.readValue(response.text(), AiMealDto.class);
        return aiMealMapper.toMealDto(aiEstimatedMeal);
    }

    private String createRequestPrompt(String language) throws IOException {
        ClassPathResource resource = new ClassPathResource("gemini-prompt.txt");
        // use input stream instead of file to avoid issues with classpath resources after packaging
        String generalPrompt = IOUtils.toString(
                resource.getInputStream(),
                StandardCharsets.UTF_8);
        return switch (language) {
            case "de" -> generalPrompt + "Please translate the name of the meal into German";
            case "en" -> generalPrompt;
            default -> throw new IllegalArgumentException("This should never happen, " +
                    "as the language is validated in the controller.");
        };
                
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
