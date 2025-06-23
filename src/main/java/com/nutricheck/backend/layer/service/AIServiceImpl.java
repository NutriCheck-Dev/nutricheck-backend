package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.MealDTO;
import org.springframework.stereotype.Service;

@Service
public class AIServiceImpl implements AIService {
    // TODO: Use Google Gemini API or similar for meal estimation
    @Override
    public MealDTO estimateMeal(byte[] image) {
        return null;
    }
}
