package com.nutricheck.backend.layer.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutricheck.backend.TestDataFactory;
import com.nutricheck.backend.dto.FoodProductDTO;
import com.nutricheck.backend.layer.client.mapper.OpenFoodFactsMapper;
import com.nutricheck.backend.util.FileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.client.MockRestServiceServer;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(OpenFoodFactsClient.class)
class OpenFoodFactsClientTest {

    @Autowired
    MockRestServiceServer server;
    @MockitoBean
    OpenFoodFactsMapper mapper;
    @Autowired
    OpenFoodFactsClient client;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void searchTest() throws IOException {
        String searchTerm = "potato";
        String responseRaw = FileUtil.readFileAsString("open-food-facts-example.json");

        server.expect(requestTo("https://world.openfoodfacts.org/cgi/search.pl?action=process&search_terms=" + searchTerm +
                        "&nutriment_0=carbohydrates&nutriment_compare_0=gt&nutriment_value_0=0&nutriment_1=proteins&nutriment_compare_1=gt&nutriment_value_1=0" +
                        "&nutriment_2=fat&nutriment_compare_2=gt&nutriment_value_2=0&nutriment_3=energy-kcal&nutriment_compare_3=gt&nutriment_value_3=0" +
                        "&sort_by=unique_scans_n&page=1&page_size=20&json=1"))
                .andRespond(withSuccess(responseRaw, MediaType.APPLICATION_JSON));

        List<FoodProductDTO> expectedProducts = List.of(
                TestDataFactory.createFoodProductDTOOneFromOpenFoodFacts(),
                TestDataFactory.createFoodProductDTOTwoFromOpenFoodFacts()
        );

        given(mapper.toDTO(anyList()))
                .willReturn(expectedProducts);

        List<FoodProductDTO> result = client.search(searchTerm, "en");
        assertEquals(expectedProducts, result);
    }
}
