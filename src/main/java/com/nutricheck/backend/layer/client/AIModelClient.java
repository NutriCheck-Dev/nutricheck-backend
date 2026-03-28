package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.MealDto;

import java.io.IOException;

/**
 * Client interface for interacting with an external AI model over a REST API.
 */
public interface AIModelClient {
    /**
     * Estimates a meal based on an image using the AI model.
     *
     * @param image the byte array of the image file of the meal to be estimated.
     * @param language the language in which to perform the estimation.
     * @throws IOException if there is an error parsing the returned JSON.
     * @return a MealDto object containing the estimated meal information.
     */
    MealDto estimateMeal(byte[] image, String language) throws IOException;
}
