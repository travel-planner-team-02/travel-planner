package com.laioffer.travelplannerbe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Table(name = "trip_site")
public class TripSiteEntity {
    @EmbeddedId
    private TripSiteKey id;

    @ManyToOne
    @MapsId("tripId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "trip_id", foreignKey = @ForeignKey(name = "fk_trip_id"), insertable = false, updatable = false)
    private TripEntity trip;

    @ManyToOne
    @MapsId("siteId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "site_id", foreignKey = @ForeignKey(name = "fk_site_id"), insertable = false, updatable = false)
    private SiteEntity site;

    private LocalDate visitDate;

    public TripSiteEntity() {}

    public TripSiteEntity(TripSiteKey id, TripEntity trip, SiteEntity site, LocalDate visitDate) {
        this.id = id;
        this.trip = trip;
        this.site = site;
        this.visitDate = visitDate;
    }

    public TripSiteKey getId() {
        return id;
    }

    public void setId(TripSiteKey id) {
        this.id = id;
    }

    public TripEntity getTrip() {
        return trip;
    }

    public void setTrip(TripEntity trip) {
        this.trip = trip;
    }

    public SiteEntity getSite() {
        return site;
    }

    public void setSite(SiteEntity site) {
        this.site = site;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TripSiteEntity)) return false;
        TripSiteEntity that = (TripSiteEntity) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(visitDate, that.visitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, visitDate);
    }

    @Override
    public String toString() {
        return "TripSiteEntity{" +
                "tripId=" + (trip != null ? trip.getId() : "null") +
                ", siteId=" + (site != null ? site.getId() : "null") +
                ", siteName=" + (site != null ? site.getName() : "null") +
                ", visitDate=" + visitDate +
                '}';
    }
}
