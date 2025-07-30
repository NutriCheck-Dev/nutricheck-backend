package com.nutricheck.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.dto.MealDTO;
import com.nutricheck.backend.layer.client.impl.GeminiClient;
import com.nutricheck.backend.layer.client.mapper.AIMealMapper;
import org.apache.commons.io.FileUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * This is the entry point for the Nutricheck application.
 */
@SpringBootApplication
public class NutricheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(NutricheckApplication.class, args);
		GeminiClient geminiClient = new GeminiClient("test",
				new ObjectMapper(), Mappers.getMapper(AIMealMapper.class));
		ClassPathResource resource = new ClassPathResource("spaghetti.png");
		MealDTO estimatedMeal = null;
		try {
			estimatedMeal = geminiClient.estimateMeal(FileUtils.readFileToByteArray(resource.getFile()));
			System.out.println("The estimated MealDTO: " + estimatedMeal.toString());
		} catch (IOException e) {
			System.out.println("An error occurred while interacting with the Gemini API " + e.getMessage());
		}
	}

}
