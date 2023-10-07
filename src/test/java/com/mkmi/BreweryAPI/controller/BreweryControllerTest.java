package com.mkmi.BreweryAPI.controller;

import com.mkmi.BreweryApi.model.Brewery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class BreweryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize the mocks

        // Mock the behavior of the RestTemplate
        Brewery brewery1 = new Brewery();
        brewery1.setName("Sample Brewery 1");
        brewery1.setStateProvince("California");
        brewery1.setPostalCode("12345");
        
        Brewery brewery2 = new Brewery();
        brewery2.setName("Sample Brewery 2");
        brewery2.setStateProvince("New York");
        brewery2.setPostalCode("67890");

        Brewery[] mockBreweries = {brewery1, brewery2};

        when(restTemplate.getForEntity("https://api.openbrewerydb.org/v1/breweries?by_state=California", Brewery[].class))
            .thenReturn(ResponseEntity.ok(mockBreweries));
    }

    @Test
    public void testGetBreweries() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/breweries")
                .param("state", "California")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
