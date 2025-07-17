package com.nutricheck.backend.layer.client;

import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(SwissFoodCompositionDatabaseClient.class)
class SwissFoodCompositionDatabaseClientTest {


    @Autowired
    MockRestServiceServer server;
    @MockitoBean
    SwissFoodCompositionDatabaseClient client;


    @Test
    void searchTest() {
        // given
        String searchTerm = "potato";
        String response = TestDataFactory.createDefaultSwissFoodCDResponse();
        String firstProduct = TestDataFactory.createDefaultSwissFoodCDFoodProductOne();
        int firstProductId = TestDataFactory.createDefaultSwissFoodCDFoodProductOneId();
        String secondProduct = TestDataFactory.createDefaultSwissFoodCDFoodProductTwo();
        int secondProductId = TestDataFactory.createDefaultSwissFoodCDFoodProductTwoId();

        // when
        server.expect(requestTo( "https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/foods?search=" + searchTerm + "&lang=en&limit=20"))
                .andRespond(withSuccess(response, MediaType.APPLICATION_JSON));

        server.expect(requestTo("https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/food/" + firstProductId + "?lang=en"))
                .andRespond(withSuccess(firstProduct, MediaType.APPLICATION_JSON));

        server.expect(requestTo("https://api.webapp.prod.blv.foodcase-services.com/BLV_WebApp_WS/webresources/BLV-api/food/" + secondProductId + "?lang=en"))
                .andRespond(withSuccess(secondProduct, MediaType.APPLICATION_JSON));

        //then
        List<FoodProductDTO> result = client.search(searchTerm);
        assertEquals(2, result.size());
        assertEquals("Mashed potatoes, instant, prepared (with water and butter)", result.getFirst().getName());
        assertEquals(11.5, result.getFirst().getCarbohydrates());
        assertEquals(8.4, result.get(1).getFat());
        assertEquals(139, result.get(1).getCalories());
    }
}