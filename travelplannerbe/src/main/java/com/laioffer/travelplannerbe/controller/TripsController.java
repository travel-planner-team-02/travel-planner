package com.laioffer.travelplannerbe.controller;

import com.laioffer.travelplannerbe.model.TripDTO;
import com.laioffer.travelplannerbe.model.UserEntity;
import com.laioffer.travelplannerbe.service.TripsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripsController {

    private final TripsService tripsService;

    public TripsController(TripsService tripsService) {
        this.tripsService = tripsService;
    }

    @GetMapping
    public List<TripDTO> getTripsByUserId(@AuthenticationPrincipal UserEntity user) {
        return tripsService.getTripsByUserId(user.getId());
    }
}
