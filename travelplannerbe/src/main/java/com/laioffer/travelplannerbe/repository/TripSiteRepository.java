package com.laioffer.travelplannerbe.repository;

import com.laioffer.travelplannerbe.model.TripSiteEntity;
import com.laioffer.travelplannerbe.model.TripSiteKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface TripSiteRepository extends JpaRepository<TripSiteEntity, TripSiteKey> {

}
