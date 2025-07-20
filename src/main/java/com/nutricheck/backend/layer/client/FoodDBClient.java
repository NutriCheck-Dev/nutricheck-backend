package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.FoodProductDTO;
import java.util.List;

/**
 * Client interface for interacting with the FoodDB API.
 */

public interface FoodDBClient {

    /**
     * Searches for food products based on a request string.
     * @param request the search request string, which can be a food name
     * @return a list of FoodProductDTO objects that match the search criteria
     */
    List<FoodProductDTO> search(String request, String language);

}
