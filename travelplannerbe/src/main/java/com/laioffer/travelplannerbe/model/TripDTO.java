package com.laioffer.travelplannerbe.model;

import java.time.LocalDate;

public record TripDTO(
    long id,
    LocalDate tripStartDate,
    LocalDate tripEndDate,
    long cityId
) {
    public static TripDTO fromTripEntity(TripEntity tripEntity) {
        return new TripDTO(
                tripEntity.getId(),
                tripEntity.getTripStartDate(),
                tripEntity.getTripEndDate(),
                tripEntity.getCityId());
    }
}
