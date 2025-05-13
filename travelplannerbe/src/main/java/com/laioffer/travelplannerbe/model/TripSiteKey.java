package com.laioffer.travelplannerbe.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TripSiteKey  implements java.io.Serializable{
    @Column(name = "trip_id")
    private Long tripId;

    @Column(name = "site_id")
    private Long siteId;

    public TripSiteKey() {
    }
    public TripSiteKey(Long tripId, Long siteId) {
        this.tripId = tripId;
        this.siteId = siteId;
    }
     public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
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
