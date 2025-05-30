package com.laioffer.travelplannerbe.service;

import com.laioffer.travelplannerbe.exception.UserNotFoundException;
import com.laioffer.travelplannerbe.model.*;
import com.laioffer.travelplannerbe.repository.SiteRepository;
import com.laioffer.travelplannerbe.repository.TripRepository;
import com.laioffer.travelplannerbe.repository.TripSiteRepository;
import com.laioffer.travelplannerbe.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TripsService {
    TripRepository tripRepository;
    UserRepository userRepository;
    TripSiteRepository tripSiteRepository;
    SiteRepository siteRepository;

    public TripsService(
            TripRepository tripRepository,
            UserRepository userRepository,
            TripSiteRepository tripSiteRepository,
            SiteRepository siteRepository
    ) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.tripSiteRepository = tripSiteRepository;
        this.siteRepository = siteRepository;
    }
    // use TripEntity to get TripDTO
    public List<TripDTO> getTripsByUserId(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        List<TripEntity> tripEntities = tripRepository.findByUserId(userId);
        return tripEntities.stream()
                            .map(TripDTO::fromTripEntity)
                            .toList();
    }
    // use tripId to get the TripSiteEntity, then use the siteId to get the siteEntity
    public List<SiteDTO> getSitesByTripId(Long userId, Long tripId) {
        TripEntity trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found"));

        if (!trip.getUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not your trip");
        }

        List<TripSiteEntity> tripSites = tripSiteRepository.findByTripId(tripId);

        return tripSites.stream()
                .map(SiteDTO::fromSiteEntity)
                .toList();
    }

    // user creates trip
    public Long createTrip(long userId, TripCreationRequest tripCreationRequest) {
        TripEntity trip = new TripEntity();
        trip.setUserId(userId);
        trip.setCityId(tripCreationRequest.cityId());
        trip.setTripStartDate(tripCreationRequest.tripStartDate());
        trip.setTripEndDate(tripCreationRequest.tripEndDate());

        TripEntity savedTrip = tripRepository.save(trip);

        return savedTrip.getId();
    }
}
