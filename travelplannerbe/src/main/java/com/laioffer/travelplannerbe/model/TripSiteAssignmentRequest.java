package com.laioffer.travelplannerbe.model;

import java.time.LocalDate;

public record TripSiteAssignmentRequest(
        long tripId,
        long siteId,
        LocalDate visitDate
) {
}
