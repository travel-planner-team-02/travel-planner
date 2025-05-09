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
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "trip_id", foreignKey = @ForeignKey(name = "fk_trip_id"), insertable = false, updatable = false)
    private TripEntity trip;

    @ManyToOne
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
}
