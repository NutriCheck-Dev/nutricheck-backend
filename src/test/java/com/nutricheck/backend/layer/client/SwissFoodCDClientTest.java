package com.nutricheck.backend.layer.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.external.SwissFoodCDResponseDTO;
import com.nutricheck.backend.layer.client.impl.SwissFoodCDClient;
import com.nutricheck.backend.layer.client.mapper.SwissFoodCDMapper;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.client.MockRestServiceServer;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(SwissFoodCDClient.class)
class SwissFoodCDClientTest {


    @Autowired
    MockRestServiceServer server;
    @MockitoBean
    SwissFoodCDMapper mapper;
    @Autowired
    SwissFoodCDClient client;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    void searchEnTest() throws Exception {
        String searchTerm = "potato";
        ClassPathResource resource1 = new ClassPathResource("swiss-search-response-example.json");
        ClassPathResource resource2 = new ClassPathResource("swiss-food-product-one-example.json");
        ClassPathResource resource3 = new ClassPathResource("swiss-food-product-two-example.json");
        String responseRaw = FileUtils.readFileToString(resource1.getFile(), StandardCharsets.UTF_8);
        String firstProductRaw = FileUtils.readFileToString(resource2.getFile(), StandardCharsets.UTF_8);
        String secondProductRaw = FileUtils.readFileToString(resource3.getFile(), StandardCharsets.UTF_8);

        server.expect(requestTo( "https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/foods?search=" +
                        searchTerm + "&lang=en&limit=40"))
                .andRespond(withSuccess(responseRaw, MediaType.APPLICATION_JSON));
        List<SwissFoodCDResponseDTO> response = objectMapper.readValue(responseRaw, new TypeReference<>() {});

        server.expect(requestTo("https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/food/" +
                        response.get(0).getId() + "?lang=en"))
                .andRespond(withSuccess(firstProductRaw, MediaType.APPLICATION_JSON));

        server.expect(requestTo("https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/food/" +
                        response.get(1).getId() + "?lang=en"))
                .andRespond(withSuccess(secondProductRaw, MediaType.APPLICATION_JSON));

        List<FoodProductDTO> expectedProducts = List.of(
                TestDataFactory.createFoodProductDTOOneFromSwissDB(),
                TestDataFactory.createFoodProductDTOTwoFromSwissDB());
        // mapper will be tested with own unit tests
        given(mapper.toFoodProductDTO(anyList()))
                .willReturn(expectedProducts);

        List<FoodProductDTO> result = client.search(searchTerm, "en");
        assertEquals(expectedProducts, result);
    }

    @Test
    void searchDeTest() throws Exception {
        String searchTerm = "Kartoffel";
        ClassPathResource resource1 = new ClassPathResource("swiss-search-response-de-example.json");
        ClassPathResource resource2 = new ClassPathResource("swiss-food-product-one-de-example.json");
        ClassPathResource resource3 = new ClassPathResource("swiss-food-product-two-de-example.json");
        String responseRaw = FileUtils.readFileToString(resource1.getFile(), StandardCharsets.UTF_8);
        String firstProductRaw = FileUtils.readFileToString(resource2.getFile(), StandardCharsets.UTF_8);
        String secondProductRaw = FileUtils.readFileToString(resource3.getFile(), StandardCharsets.UTF_8);


        server.expect(requestTo( "https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/foods?search=" +
                        searchTerm + "&lang=de&limit=40"))
                .andRespond(withSuccess(responseRaw, MediaType.APPLICATION_JSON));

        List<SwissFoodCDResponseDTO> response = objectMapper.readValue(responseRaw, new TypeReference<>() {});

        server.expect(requestTo("https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/food/" +
                        response.get(0).getId() + "?lang=de"))
                .andRespond(withSuccess(firstProductRaw, MediaType.APPLICATION_JSON));

        server.expect(requestTo("https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/food/" +
                        response.get(1).getId() + "?lang=de"))
                .andRespond(withSuccess(secondProductRaw, MediaType.APPLICATION_JSON));

        List<FoodProductDTO> expectedProducts = List.of(
                TestDataFactory.createFoodProductDTOOneFromSwissDB(),
                TestDataFactory.createFoodProductDTOTwoFromSwissDB());
        // mapper will be tested with own unit tests
        given(mapper.toFoodProductDTO(anyList()))
                .willReturn(expectedProducts);

        List<FoodProductDTO> result = client.search(searchTerm, "de");
        assertEquals(expectedProducts, result);
    }
}