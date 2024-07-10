package com.fernando.nlw.planner_api.models;

import java.util.UUID;
import com.fernando.nlw.planner_api.models.base.TripResource;
import com.fernando.nlw.planner_api.requests.LinkRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "links")
@NoArgsConstructor
@Getter
@Setter
public class Link extends TripResource {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String url;

    public Link(UUID id, String title, String url, Trip trip) {
        super(id, trip);
        this.title = title;
        this.url = url;
    }

    public Link(Trip trip, LinkRequest request) {
        this.trip = trip;
        this.title = request.title();
        this.url = request.url();
    }
}