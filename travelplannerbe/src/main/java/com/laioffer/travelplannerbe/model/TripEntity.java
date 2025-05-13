package com.laioffer.travelplannerbe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "trips")
public class TripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private LocalDate tripStartDate;
    private LocalDate tripEndDate;
    @Column(name = "city_id")
    private Long cityId;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"), insertable = false, updatable = false)
    private UserEntity user;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "city_id", foreignKey = @ForeignKey(name = "fk_city_id"), insertable = false, updatable = false)
    private CityEntity city;

    public TripEntity() {
    }

    public TripEntity(Long id, Long userId, LocalDate tripStartDate, LocalDate tripEndDate, Long cityId) {
        this.id = id;
        this.userId = userId;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.cityId = cityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(LocalDate tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public LocalDate getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(LocalDate tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TripEntity that = (TripEntity) o;
    return Objects.equals(id, that.id)
            && Objects.equals(userId, that.userId)
            && Objects.equals(cityId, that.cityId)
            && Objects.equals(tripStartDate, that.tripStartDate)
            && Objects.equals(tripEndDate, that.tripEndDate);
}

@Override
public int hashCode() {
    return Objects.hash(id, userId, cityId, tripStartDate, tripEndDate);
}

@Override
public String toString() {
    return "TripEntity{" +
            "id=" + id +
            ", userId=" + userId +
            ", cityId=" + cityId +
            ", cityName=" + (city != null ? city.getName() : "null") +
            ", tripStartDate=" + tripStartDate +
            ", tripEndDate=" + tripEndDate +
            '}';
}

}
