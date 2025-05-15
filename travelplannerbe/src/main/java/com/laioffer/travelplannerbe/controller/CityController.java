package com.laioffer.travelplannerbe.controller;

import com.laioffer.travelplannerbe.model.CityDTO;
import com.laioffer.travelplannerbe.repository.CityRepository;
import com.laioffer.travelplannerbe.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/{id}")
    public CityDTO getCityNameAndLocationById(@PathVariable Long id) {
        return cityService.getCityNameAndLocation(id);
    }
}
