package com.nutricheck.backend.layer.client;


import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Schema;
import com.nutricheck.backend.dto.AIMealDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient implements AIModelClient {
    private final Client geminiClient;

    public GeminiClient(@Value("${gemini.api.key}") String geminiApiKey) {
        this.geminiClient = Client.builder()
                .apiKey(geminiApiKey)
                .build();
    }
    @Override
    public AIMealDTO estimateMeal(byte[] image) {
        GenerateContentConfig config = GenerateContentConfig.builder()
                .candidateCount(1)
                .responseMimeType("application/json")
                .responseSchema(null) // TODO: Set Json schema for AIMealDTO
                .build();
        return null;
    }
}
