package com.laioffer.travelplannerbe.model;

import jakarta.persistence.*;

import org.locationtech.jts.geom.Point;
import java.util.Objects;

@Entity
@Table(name = "cities")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "geometry(Point,4326)")
    private Point location;
    //one city can be mapped to many trips
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripEntity> trips = new ArrayList<>();

    public CityEntity() {
    }

    public CityEntity(Long id, String name, Point location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    //we don't need setter methods because we do not want to change
    //the name or loaction after the city is created
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Point getLocation() {
        return location;
    }
    
    public List<TripEntity> getTrips() {
        return trips;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location=" + location +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityEntity)) return false;
        CityEntity that = (CityEntity) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,name,location);
    }
}
