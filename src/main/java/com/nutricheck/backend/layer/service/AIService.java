package com.nutricheck.backend.layer.service;

import com.nutricheck.backend.dto.MealDTO;

public interface AIService {
    MealDTO estimateMeal(byte[] image); // TODO: Define correct parameter
}
