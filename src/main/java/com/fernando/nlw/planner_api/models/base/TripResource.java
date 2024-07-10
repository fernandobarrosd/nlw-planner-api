package com.fernando.nlw.planner_api.models.base;

import java.util.UUID;
import com.fernando.nlw.planner_api.models.Trip;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class TripResource extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "trip_id", nullable = false)
    protected Trip trip;

    public TripResource(UUID id, Trip trip) {
        super(id);
        this.trip = trip;
    }

    
}