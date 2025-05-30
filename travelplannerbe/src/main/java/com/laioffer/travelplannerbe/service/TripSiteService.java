package com.laioffer.travelplannerbe.service;

import com.laioffer.travelplannerbe.model.*;
import com.laioffer.travelplannerbe.repository.SiteRepository;
import com.laioffer.travelplannerbe.repository.TripRepository;
import com.laioffer.travelplannerbe.repository.TripSiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripSiteService {
    TripSiteRepository tripSiteRepository;
    TripRepository tripRepository;
    SiteRepository siteRepository;

    public TripSiteService(
            TripSiteRepository tripSiteRepository,
            TripRepository tripRepository,
            SiteRepository siteRepository) {
        this.tripSiteRepository = tripSiteRepository;
        this.tripRepository = tripRepository;
        this.siteRepository = siteRepository;
    }

    public void assignSitesToTrip(Long userId, Long tripId, List<TripSiteAssignmentRequest> assignments) {
        TripEntity trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (!trip.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized to modify this trip");
        }

        for (TripSiteAssignmentRequest req : assignments) {
            TripSiteKey key = new TripSiteKey(tripId, req.siteId());
            TripSiteEntity tripSite = new TripSiteEntity();
            tripSite.setId(key);
            tripSite.setTrip(trip);

            SiteEntity site = siteRepository.findById(req.siteId())
                    .orElseThrow(() -> new RuntimeException("Site not found"));
            tripSite.setSite(site);
            tripSite.setVisitDate(req.visitDate());

            tripSiteRepository.save(tripSite);
        }
    }

}
