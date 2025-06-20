package com.nutricheck.backend.layer.controller;

import com.nutricheck.backend.layer.service.MealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("nutricheck/user/search/")
public class MealController {

    private final MealService mealService;

    public MealController(MealService mealService) {
        this.mealService = mealService;
    }
}
