package com.fernando.nlw.planner_api.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;
import com.fernando.nlw.planner_api.models.base.BaseEntity;
import com.fernando.nlw.planner_api.requests.TripRequest;
import com.fernando.nlw.planner_api.utils.DateUtils;

@Entity
@Table(name = "trips")
@NoArgsConstructor
@Getter
@Setter
public class Trip extends BaseEntity {
    @Column(nullable = false)
    private String destination;

    @Column(name = "starts_at", nullable = false)
    private LocalDateTime startsAt;

    @Column(name = "ends_at", nullable = false)
    private LocalDateTime endsAt;

    @Column(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "owner_email", nullable = false)
    private String ownerEmail;

    
    public Trip(UUID id, String destination, LocalDateTime startsAt, LocalDateTime endsAt, Boolean isConfirmed,
        String ownerName, String ownerEmail) {
        super(id);
        this.destination = destination;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.isConfirmed = isConfirmed;
        this.ownerName = ownerName;
        this.ownerEmail = ownerEmail;
    }

    public Trip(TripRequest tripRequest) {
        this.destination = tripRequest.destination();
        this.ownerName = tripRequest.ownerName();
        this.ownerEmail = tripRequest.ownerEmail();
        this.isConfirmed = false;
        this.startsAt = DateUtils.convertToLocalDateTime(tripRequest.startsAt());
        this.endsAt = DateUtils.convertToLocalDateTime(tripRequest.endsAt());
    }
}