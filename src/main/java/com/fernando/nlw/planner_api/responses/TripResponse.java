package com.fernando.nlw.planner_api.responses;

import com.fernando.nlw.planner_api.models.Trip;
import lombok.Builder;

@Builder
public record TripResponse(
        String tripID,
        String destination,
        String startsAt,
        String endsAt,
        Boolean isConfirmed,
        String ownerName,
        String ownerEmail) {

    public static TripResponse fromEntity(Trip trip) {
        return TripResponse.builder()
                .tripID(trip.getId().toString())
                .destination(trip.getDestination())
                .startsAt(trip.getStartsAt().toString())
                .endsAt(trip.getEndsAt().toString())
                .ownerName(trip.getOwnerName())
                .ownerEmail(trip.getOwnerEmail())
                .isConfirmed(trip.getIsConfirmed())
                .build();
    }
}
