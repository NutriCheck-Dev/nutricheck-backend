package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.MealDTO;

/**
 * Client interface for interacting with an external AI model over a REST API.
 */
public interface AIModelClient {
    /**
     * Estimates a meal based on an image using the AI model.
     *
     * @param image the byte array of the image file of the meal to be estimated.
     * @return a MealDTO object containing the estimated meal information.
     */
    MealDTO estimateMeal(byte[] image);
}
