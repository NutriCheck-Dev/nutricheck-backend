package com.nutricheck.backend.layer.client;

import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsApi;
import com.alpermulayim.openfoodfacts_spring_boot_starter.OpenFoodFactsWebClient;
import com.alpermulayim.openfoodfacts_spring_boot_starter.config.OpenFoodFactsWebClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenFoodFactsConfig {

    OpenFoodFactsWebClientProperties properties;

    public OpenFoodFactsConfig(OpenFoodFactsWebClientProperties properties) {
        this.properties = properties;
    }

    @Bean
    OpenFoodFactsApi openFoodFactsWebClient(){
        return new OpenFoodFactsWebClient(properties);
    }

}
