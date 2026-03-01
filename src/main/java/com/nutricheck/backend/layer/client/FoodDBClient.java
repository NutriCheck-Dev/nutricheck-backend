package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.FoodProductDto;

import java.util.List;

/**
 * Client interface for interacting with the FoodDB API.
 */

public interface FoodDBClient {

    /**
     * Searches for food products based on a request string.
     * @param request the search request string, which can be a food name
     * @param language the language in which the search should be performed
     * @return a list of FoodProductDto objects that match the search criteria
     */
    List<FoodProductDto> search(String request, String language);

}
