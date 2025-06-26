package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.FoodProductDTO;
import java.util.List;

public interface FoodDBClient {

    List<FoodProductDTO> search(String request);

}
