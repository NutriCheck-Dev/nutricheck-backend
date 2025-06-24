package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.layer.client.AIModelClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {
    private final AIModelClient aiModelClient;
    @Override
    public MealDTO estimateMeal(String encodedImage) {
        return null;
    }
}
