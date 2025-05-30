package com.laioffer.travelplannerbe.model;

import java.time.LocalDate;

public record TripCreationRequest(
        long cityId,
        LocalDate tripStartDate,
        LocalDate tripEndDate
) {
}
