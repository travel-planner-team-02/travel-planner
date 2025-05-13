package com.laioffer.travelplannerbe.repository;

import com.laioffer.travelplannerbe.model.TripSiteEntity;
import com.laioffer.travelplannerbe.model.TripSiteKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TripSiteRepository extends JpaRepository<TripSiteEntity, TripSiteKey> {
    //get a list of site_id by trip_id
    @Query("SELECT ts.id.siteId FROM TripSiteEntity ts WHERE ts.trip.id = :tripId")
    List<Long> findSiteIdsByTripId(@Param("tripId") Long tripId);
}
