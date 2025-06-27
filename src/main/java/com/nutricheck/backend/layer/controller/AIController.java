package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.layer.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/ai")
public class AIController {
    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping(value = "/estimate", consumes = "text/plain")
    public ResponseEntity<MealDTO> estimateMeal(@RequestBody String encodedImage) {
        return null;
    }
}
