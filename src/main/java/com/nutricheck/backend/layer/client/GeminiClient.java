package com.nutricheck.backend.layer.client;

import com.google.genai.Client;
import com.nutricheck.backend.dto.AIMealDTO;
import org.springframework.stereotype.Component;

@Component
public class GeminiClient implements AIModelClient {
    private Client geminiClient;

    public GeminiClient() {
        // TODO: Init Google Gemini client
    }
    @Override
    public AIMealDTO estimateMeal(String encodedImage) {
        return null;
    }
}
