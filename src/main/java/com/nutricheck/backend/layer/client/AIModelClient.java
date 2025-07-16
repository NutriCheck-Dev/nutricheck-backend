package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.dto.AIMealDTO;

public interface AIModelClient {
    AIMealDTO estimateMeal(byte[] image);
}
