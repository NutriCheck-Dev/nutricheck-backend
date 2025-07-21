package com.nutricheck.backend.layer.client;

import com.google.genai.Client;
import com.nutricheck.backend.dto.AIMealDTO;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient implements AIModelClient {
    private final Client geminiClient;

    public GeminiClient() {
        this.geminiClient = null;
    }
    @Override
    public AIMealDTO estimateMeal(byte[] image) {
        return null;
    }
}
