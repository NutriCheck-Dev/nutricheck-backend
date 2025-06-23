package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.layer.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nutricheck/user/ai")
public class AIController {
    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    // FIXME: How to pass picture? What does Gemini accept?
    @PostMapping("/estimate")
    public ResponseEntity<MealDTO> estimateMeal() {
        return null;
    }
}
