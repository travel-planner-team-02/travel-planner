package com.laioffer.travelplannerbe.controller;

import com.laioffer.travelplannerbe.model.CityDTO;
import com.laioffer.travelplannerbe.repository.CityRepository;
import com.laioffer.travelplannerbe.service.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.laioffer.travelplannerbe.model.SiteDTO;
import com.laioffer.travelplannerbe.service.SiteService;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cities")
public class CityController {

    private final CityService cityService;
    private final SiteService siteService;

    public CityController(CityService cityService, SiteService siteService) {
        this.cityService = cityService;
        this.siteService = siteService;
    }

    @GetMapping("/{id}")
    public CityDTO getCityNameAndLocationById(@PathVariable Long id) {
        return cityService.getCityNameAndLocation(id);
    }

    @GetMapping("/{id}/sites")
    public List<SiteDTO> getSitesByCity(@PathVariable("id")  Long cityId) {
    return siteService.getSitesByCityId(cityId);
    }

    @GetMapping
    public List<CityDTO> getAllCities() {
    return cityService.getAllCities();
    }
}
