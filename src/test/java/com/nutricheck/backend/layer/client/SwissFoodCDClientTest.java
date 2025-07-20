package com.nutricheck.backend.layer.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.dto.SwissFoodCDResponseDTO;
import com.nutricheck.backend.layer.client.mapper.SwissFoodCDMapper;
import com.nutricheck.backend.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;
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
    void searchTestEn() throws IOException {
        String searchTerm = "potato";
        String responseRaw = FileUtil.readFileAsString("swiss-search-response-example.json");
        String firstProductRaw = FileUtil.readFileAsString("swiss-food-product-one-example.json");
        String secondProductRaw = FileUtil.readFileAsString("swiss-food-product-two-example.json");

        server.expect(requestTo( "https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/foods?search=" +
                        searchTerm + "&lang=en&limit=20"))
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
        given(mapper.toDTO(anyList()))
                .willReturn(expectedProducts);

        List<FoodProductDTO> result = client.search(searchTerm, "en");
        assertEquals(result, expectedProducts);
    }

    @Test
    void seachTestDe() throws IOException {
        String searchTerm = "Kartoffel";
        String responseRaw = FileUtil.readFileAsString("swiss-search-response-de-example.json");
        String firstProductRaw = FileUtil.readFileAsString("swiss-food-product-one-de-example.json");
        String secondProductRaw = FileUtil.readFileAsString("swiss-food-product-two-de-example.json");

        server.expect(requestTo( "https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/foods?search=" +
                        searchTerm + "&lang=de&limit=20"))
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
        given(mapper.toDTO(anyList()))
                .willReturn(expectedProducts);

        List<FoodProductDTO> result = client.search(searchTerm, "de");
        assertEquals(result, expectedProducts);

    }
}