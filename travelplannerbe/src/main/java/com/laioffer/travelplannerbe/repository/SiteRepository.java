package com.laioffer.travelplannerbe.repository;

import com.laioffer.travelplannerbe.model.SiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SiteRepository extends JpaRepository<SiteEntity, Long> {

    List<SiteEntity> findByCityName(String cityName);

    List<SiteEntity> findByName(String name);
}
