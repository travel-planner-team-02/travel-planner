package com.laioffer.travelplannerbe.model;

import org.locationtech.jts.geom.Point;

public record CityDTO(
        String name,
        GeoPoint location
) {
    public static CityDTO fromCityEntity(CityEntity cityEntity) {
        Point location = cityEntity.getLocation();
        return new CityDTO(
                cityEntity.getName(),
                new GeoPoint(location.getCoordinate().y, location.getCoordinate().x)
        );
    }
}
