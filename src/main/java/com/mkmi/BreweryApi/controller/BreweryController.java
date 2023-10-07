package com.mkmi.BreweryApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.mkmi.BreweryApi.model.Brewery;

import java.util.List;

@RestController
@RequestMapping("/breweries")
public class BreweryController {

    private static final String API_URL = "https://api.openbrewerydb.org/v1/breweries";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<List<Brewery>> getBreweries(
            @RequestParam(name = "state", required = false) String state,
            @RequestParam(name = "zipcode", required = false) String zip,
            @RequestParam(name = "type", required = false) String type) {
        String apiUrl = API_URL;

        if (state != null && !state.isEmpty()) {
            apiUrl += appendParameter(apiUrl, "by_state=" + state);
        }
        if (zip != null && !zip.isEmpty()) {
            apiUrl += appendParameter(apiUrl, "by_postal=" + zip);
        }
        if (type != null && !type.isEmpty()) {
            apiUrl += appendParameter(apiUrl, "by_type=" + type);
        }

        ResponseEntity<Brewery[]> response = restTemplate.getForEntity(apiUrl, Brewery[].class);
        Brewery[] breweries = response.getBody();

        return ResponseEntity.ok(List.of(breweries));
    }

    private String appendParameter(String apiUrl, String parameter) {
        if (apiUrl.contains("?")) {
            return "&" + parameter;
        } else {
            return "?" + parameter;
        }
    }
    
}
