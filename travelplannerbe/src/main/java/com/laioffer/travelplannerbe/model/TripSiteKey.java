package com.laioffer.travelplannerbe.model;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class TripSiteKey  implements java.io.Serializable{
    private Long tripId;
    private Long siteId;

    public TripSiteKey() {
    }
    public TripSiteKey(Long tripId, Long siteId) {
        this.tripId = tripId;
        this.siteId = siteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripSiteKey that = (TripSiteKey) o;
        return Objects.equals(tripId, that.tripId) &&
                Objects.equals(siteId, that.siteId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, siteId);
    }
}
