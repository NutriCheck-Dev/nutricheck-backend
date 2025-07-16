package com.nutricheck.backend.layer.client;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Schema;
import com.nutricheck.backend.dto.AIMealDTO;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient implements AIModelClient {
    private final Client geminiClient;

    public GeminiClient() {
        this.geminiClient = new Client();
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
