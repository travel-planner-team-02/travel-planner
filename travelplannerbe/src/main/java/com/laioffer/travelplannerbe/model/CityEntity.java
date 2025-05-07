package com.laioffer.travelplannerbe.model;

import jakarta.persistence.*;

import java.awt.*;

@Entity
@Table(name = "cities")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Point location;

    public CityEntity() {
    }

    public CityEntity(Long id, String name, Point location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }
}
