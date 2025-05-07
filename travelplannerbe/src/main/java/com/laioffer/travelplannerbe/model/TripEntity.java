package com.laioffer.travelplannerbe.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

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

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_user_id"), insertable = false, updatable = false)
    private UserEntity user;

    public TripEntity() {
    }

    public TripEntity(Long id, Long userId,LocalDate tripStartDate, LocalDate tripEndDate) {
        this.id = id;
        this.userId = userId;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
    }


}
