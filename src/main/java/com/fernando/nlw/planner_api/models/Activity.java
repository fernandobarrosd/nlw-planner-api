package com.fernando.nlw.planner_api.models;

import java.time.LocalDateTime;
import java.util.UUID;
import com.fernando.nlw.planner_api.models.base.TripResource;
import com.fernando.nlw.planner_api.requests.ActivityRequest;
import com.fernando.nlw.planner_api.utils.DateUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "activities")
@Getter
@Setter
@NoArgsConstructor
public class Activity extends TripResource {
    @Column(nullable = false)
    private String title;

    @Column(name = "accurs_at", nullable = false)
    private LocalDateTime accursAt;

    @Column(name = "is_finish", nullable = false)
    private Boolean isFinish;


    public Activity(UUID id, String title, LocalDateTime accursAt, Boolean isFinish, 
    Trip trip) {
        super(id, trip);
        this.title = title;
        this.accursAt = accursAt;
        this.isFinish = isFinish;
    }

    public Activity(Trip trip, ActivityRequest request) {
        this.trip = trip;
        this.title = request.title();
        this.isFinish = false;
        this.accursAt = DateUtils.convertToLocalDateTime(request.accursAt());
    }
}