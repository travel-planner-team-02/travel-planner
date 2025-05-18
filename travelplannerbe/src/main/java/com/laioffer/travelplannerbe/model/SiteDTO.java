package com.laioffer.travelplannerbe.model;

import java.time.LocalDate;
import org.locationtech.jts.geom.Point;
import java.util.List;

public record SiteDTO(
        Long id,
        String name,
        String description,
        String address,
        List<String> imageUrls,
        Integer visitTime,
        double rating,
        LocalDate visitDate, // get from TripSiteEntity
        GeoPoint location
) {
    public static SiteDTO fromSiteEntity(TripSiteEntity tripSiteEntity) {
        SiteEntity siteEntity = tripSiteEntity.getSite();
        Point location = siteEntity.getLocation();
        return new SiteDTO(
                siteEntity.getId(),
                siteEntity.getName(),
                siteEntity.getDescription(),
                siteEntity.getAddress(),
                siteEntity.getImageUrls(),
                siteEntity.getVisitTime(),
                siteEntity.getRating(),
                tripSiteEntity.getVisitDate(),
                new GeoPoint(location.getCoordinate().y, location.getCoordinate().x)
        );
    }
}
