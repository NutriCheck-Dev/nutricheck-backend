package com.nutricheck.backend.layer.client.impl;

import com.google.genai.Client;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.layer.client.AIModelClient;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient implements AIModelClient {
    private final Client geminiClient;

    public GeminiClient() {
        this.geminiClient = null;
    }
    @Override
    public MealDTO estimateMeal(byte[] image) {
        return null;
    }
}
