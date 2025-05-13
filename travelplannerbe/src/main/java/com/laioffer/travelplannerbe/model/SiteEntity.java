package com.laioffer.travelplannerbe.model;

import jakarta.persistence.*;

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
    private List<String> image_urls;

    @ManyToOne
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_city_id"))
    private CityEntity city;

    public SiteEntity() {
    }

    public SiteEntity(Long id, String name, String description, String address, CityEntity city, Double rating, List<String> image_urls) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.rating = rating;
        this.image_urls = image_urls;
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

//    public String getCityName() {
//        return cityName;
//    }

    public Double getRating() {
        return rating;
    }

    public List<String> getImage_urls() {
        return image_urls;
    }

    public CityEntity getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SiteEntity that = (SiteEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(address, that.address) && Objects.equals(rating, that.rating) && Objects.equals(image_urls, that.image_urls) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, address, rating, image_urls, city);
    }
//    public int hashCode() {
//        return Objects.hash(id, name, description, address, cityName, rating, image_urls, city);
//    }
}
