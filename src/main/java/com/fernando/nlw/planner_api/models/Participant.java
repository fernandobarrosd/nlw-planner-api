package com.fernando.nlw.planner_api.models;

import java.util.UUID;
import com.fernando.nlw.planner_api.models.base.TripResource;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "participants")
@Getter
@Setter
@NoArgsConstructor
public class Participant extends TripResource {
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed;

    
    public Participant(UUID id, String name, String email, Boolean isConfirmed, Trip trip) {
        super(id, trip);
        this.name = name;
        this.email = email;
        this.isConfirmed = isConfirmed;
    }

    public Participant(String email, Trip trip) {
        this.email = email;
        this.trip = trip;
        this.isConfirmed = false;
    }
}