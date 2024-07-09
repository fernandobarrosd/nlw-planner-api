package com.fernando.nlw.planner_api.services;

import com.fernando.nlw.planner_api.exceptions.EntityNotFoundException;
import com.fernando.nlw.planner_api.repositories.TripRepository;
import com.fernando.nlw.planner_api.responses.TripResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fernando.nlw.planner_api.models.Trip;
import com.fernando.nlw.planner_api.requests.TripRequest;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripService {
    private final ParticipantService participantService;
    private final TripRepository tripRepository;

    public TripResponse findTripByID(UUID tripID) {
        Optional<Trip> tripOptional = tripRepository.findById(tripID);

        if (tripOptional.isEmpty()) {
            throw new EntityNotFoundException("The trip#%s is not exists".formatted(tripID));
        }
        return tripOptional.map(TripResponse::fromEntity).get();
    }

    public TripResponse registerTrip(TripRequest tripRequest) {
        Trip trip = new Trip(tripRequest);

        this.tripRepository.save(trip);

        this.participantService.registerParticipantForEvent(tripRequest.emailsToInvite(), trip.getId());

        return TripResponse.fromEntity(trip);
    }
}
