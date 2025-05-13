package com.laioffer.travelplannerbe.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sites")
public class SiteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    private Double rating;
    private Integer visitTime; // in minutes
    @ElementCollection
    @CollectionTable(name = "site_images", joinColumns = @JoinColumn(name = "site_id"))
    @Column(name = "image_url")
    private List<String> imageUrls = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_city_id"))
    private CityEntity city;

    public SiteEntity() {
    }

    public SiteEntity(Long id, String name, String description, String address, CityEntity city,
     Double rating, List<String> imageUrls, Integer visitTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.rating = rating;
        this.imageUrls = imageUrls;
        this.visitTime = visitTime;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public Double getRating() {
        return rating;
    }

    public List<String> getimageUrls() {
        return imageUrls;
    }

    public CityEntity getCity() {
        return city;
    }

    public Integer getVisitTime() {
        return visitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SiteEntity that = (SiteEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name)
        && Objects.equals(description, that.description)
        && Objects.equals(address, that.address)
        && Objects.equals(rating, that.rating)
        && Objects.equals(imageUrls, that.imageUrls)
        && Objects.equals(visitTime, that.visitTime)
        && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, address, rating, imageUrls, city, visitTime);
    }
}
