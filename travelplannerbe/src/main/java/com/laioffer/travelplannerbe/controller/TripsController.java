package com.laioffer.travelplannerbe.controller;

import com.laioffer.travelplannerbe.model.*;
import com.laioffer.travelplannerbe.service.TripsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{tripId}/sites")
    public List<SiteDTO> getSitesByTripId(
            @AuthenticationPrincipal UserEntity user,
            @PathVariable Long tripId) {
        return tripsService.getSitesByTripId(user.getId(), tripId);
    }
}
