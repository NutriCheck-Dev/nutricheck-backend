package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.AIMealDTO;

/**
 * Interface for the interaction with an external AI model API
 */
public interface AIModelClient {
    /**
     * Estimates a meal based on an image using the external AI model.
     *
     * @param image the byte array of the image file of the meal to be estimated.
     * @return an AIMealDTO object containing the estimated meal information.
     */
    AIMealDTO estimateMeal(byte[] image);
}
