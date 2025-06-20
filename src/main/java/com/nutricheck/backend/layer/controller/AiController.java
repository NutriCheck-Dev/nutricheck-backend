package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.layer.service.AiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nutricheck/user/ai/")
public class AiController {
    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }
}
