package com.mkmi.BreweryApi.controller;

import com.mkmi.BreweryApi.model.Brewery;
import com.mkmi.BreweryApi.service.BreweryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breweries")
public class BreweryController {

    private final BreweryService breweryService;

    @Autowired
    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping("/Id")
    public Brewery getBreweryById(@PathVariable("Id") String obdbId) {
        return breweryService.getBreweryById(obdbId);
    }

    @GetMapping("/list")
    public List<Brewery> listBreweries() {
        return breweryService.getAllBreweries();
    }

    @GetMapping("/filter/city")
    public List<Brewery> filterByCity(@RequestParam("by_city") String city) {
        return breweryService.getBreweriesByCity(city);
    }

    @GetMapping("/filter/ids")
    public List<Brewery> filterByIds(@RequestParam("by_ids") List<String> breweryIds) {
        return breweryService.getBreweriesByIds(breweryIds);
    }

    @GetMapping("/filter/name")
    public List<Brewery> filterByName(@RequestParam("by_name") String name) {
        return breweryService.getBreweriesByName(name);
    }

    @GetMapping("/filter/state")
    public List<Brewery> filterByState(@RequestParam("by_state") String state) {
        return breweryService.getBreweriesByState(state);
    }
}
