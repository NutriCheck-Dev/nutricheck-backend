package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.MealDTO;

public interface AIService {
    MealDTO estimateMeal(String encodedImage);
}
