package com.mkmi.BreweryApi.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.mkmi.BreweryApi.model.Brewery;

@Service
public class BreweryService {

    private final String apiBaseUrl;
    private final RestTemplate restTemplate;

    public BreweryService(@Value("${api.openbrewerydb.url}") String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
        this.restTemplate = new RestTemplate();
    }

    public Brewery getBreweryById(String obdbId) {
        try {
            ResponseEntity<Brewery> response = restTemplate.getForEntity(buildApiUri("/v1/breweries/" + obdbId), Brewery.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., network issues, API errors)
            e.printStackTrace(); // Replace with proper logging
        }
        return null;
    }

    public List<Brewery> getAllBreweries() {
        try {
            ResponseEntity<Brewery[]> response = restTemplate.getForEntity(buildApiUri("/v1/breweries"), Brewery[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(response.getBody());
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., network issues, API errors)
            e.printStackTrace(); // Replace with proper logging
        }
        return Collections.emptyList();
    }

    public List<Brewery> getBreweriesByCity(String city) {
        try {
            ResponseEntity<Brewery[]> response = restTemplate.getForEntity(buildApiUri("/v1/breweries?by_city=" + city), Brewery[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(response.getBody());
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., network issues, API errors)
            e.printStackTrace(); // Replace with proper logging
        }
        return Collections.emptyList();
    }

    public List<Brewery> getBreweriesByIds(List<String> breweryIds) {
        try {
            String ids = String.join(",", breweryIds);
            ResponseEntity<Brewery[]> response = restTemplate.getForEntity(buildApiUri("/v1/breweries?by_ids=" + ids), Brewery[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(response.getBody());
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., network issues, API errors)
            e.printStackTrace(); // Replace with proper logging
        }
        return Collections.emptyList();
    }

    public List<Brewery> getBreweriesByName(String name) {
        try {
            ResponseEntity<Brewery[]> response = restTemplate.getForEntity(buildApiUri("/v1/breweries?by_name=" + name), Brewery[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(response.getBody());
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., network issues, API errors)
            e.printStackTrace(); // Replace with proper logging
        }
        return Collections.emptyList();
    }

    public List<Brewery> getBreweriesByState(String state) {
        try {
            ResponseEntity<Brewery[]> response = restTemplate.getForEntity(buildApiUri("/v1/breweries?by_state=" + state), Brewery[].class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(response.getBody());
            }
        } catch (Exception e) {
            // Handle exceptions (e.g., network issues, API errors)
            e.printStackTrace(); // Replace with proper logging
        }
        return Collections.emptyList();
    }

    private String buildApiUri(String path) {
        return UriComponentsBuilder.fromUriString(apiBaseUrl + path)
                // You can add query parameters if needed
                .toUriString();
    }
}
