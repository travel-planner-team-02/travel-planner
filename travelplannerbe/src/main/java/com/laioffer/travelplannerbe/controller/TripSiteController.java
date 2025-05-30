package com.laioffer.travelplannerbe.controller;

import com.laioffer.travelplannerbe.model.TripSiteAssignmentRequest;
import com.laioffer.travelplannerbe.model.UserEntity;
import com.laioffer.travelplannerbe.service.TripSiteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tripsite")
public class TripSiteController {
    TripSiteService tripSiteService;

    public TripSiteController(TripSiteService tripSiteService) {
        this.tripSiteService = tripSiteService;
    }

    @PostMapping("/{tripId}/assig-sites")
    public void assignSiteToTrip(
            @AuthenticationPrincipal UserEntity user,
            @PathVariable long tripId,
            @RequestBody List<TripSiteAssignmentRequest> assignmentRequests
            ) {
        tripSiteService.assignSitesToTrip(user.getId(), tripId, assignmentRequests);
    }
}
