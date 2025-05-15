package com.laioffer.travelplannerbe.service;

import com.laioffer.travelplannerbe.exception.UserNotFoundException;
import com.laioffer.travelplannerbe.model.TripDTO;
import com.laioffer.travelplannerbe.model.TripEntity;
import com.laioffer.travelplannerbe.repository.TripRepository;
import com.laioffer.travelplannerbe.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripsService {
    TripRepository tripRepository;
    UserRepository userRepository;

    public TripsService(TripRepository tripRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
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
}
