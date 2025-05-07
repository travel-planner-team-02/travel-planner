package com.laioffer.travelplannerbe.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "sites")
public class SiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    @Column(name = "city_name")
    private String cityName;
    private Double rating;
    private List<String> image_urls;

    @ManyToOne
    @JoinColumn(name = "city_name", foreignKey = @ForeignKey(name = "fk_city_name"), insertable = false, updatable = false)
    private CityEntity city;

    public SiteEntity() {
    }

    public SiteEntity(Long id, String name, String description, String address, String cityName, Double rating, List<String> image_urls) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.cityName = cityName;
        this.rating = rating;
        this.image_urls = image_urls;
    }
}
