package com.fernando.nlw.planner_api.services;

import com.fernando.nlw.planner_api.exceptions.EntityNotFoundException;
import com.fernando.nlw.planner_api.exceptions.TripNotConfirmedException;
import com.fernando.nlw.planner_api.repositories.TripRepository;
import com.fernando.nlw.planner_api.responses.ActivityCreatedResponse;
import com.fernando.nlw.planner_api.responses.LinkCreatedResponse;
import com.fernando.nlw.planner_api.responses.ParticipantResponse;
import com.fernando.nlw.planner_api.responses.TripResponse;
import com.fernando.nlw.planner_api.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fernando.nlw.planner_api.models.Trip;
import com.fernando.nlw.planner_api.requests.ActivityRequest;
import com.fernando.nlw.planner_api.requests.LinkRequest;
import com.fernando.nlw.planner_api.requests.TripRequest;
import com.fernando.nlw.planner_api.requests.TripUpdateRequest;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TripService {
    private final ParticipantService participantService;
    private final TripRepository tripRepository;
    private final ActivityService activityService;
    private final LinkService linkService;

    public Trip getTripEntity(UUID tripID) {
        return tripRepository.findById(tripID)
            .orElseThrow(() -> new EntityNotFoundException("The trip#%s is not exists".formatted(tripID)));
    }

    public TripResponse findTripByID(UUID tripID) {
        Trip trip = getTripEntity(tripID);
        return TripResponse.fromEntity(trip);
    }

    public TripResponse registerTrip(TripRequest tripRequest) {
        Trip trip = new Trip(tripRequest);

        this.tripRepository.save(trip);

        this.participantService.registerParticipantForEvent(tripRequest.emailsToInvite(), trip);

        return TripResponse.fromEntity(trip);
    }

    public TripResponse updateTrip(UUID tripID, TripUpdateRequest tripRequest) {
        Trip trip = getTripEntity(tripID);
        trip.setDestination(tripRequest.destination());
        trip.setStartsAt(DateUtils.convertToLocalDateTime(tripRequest.startsAt()));
        trip.setEndsAt(DateUtils.convertToLocalDateTime(tripRequest.endsAt()));
        
        tripRepository.save(trip);

        return TripResponse.fromEntity(trip);
    }

    public TripResponse confirmTrip(UUID tripID) {
        Trip trip = getTripEntity(tripID);
        trip.setIsConfirmed(true);
        this.tripRepository.save(trip);

        this.participantService.triggerConfirmationEmailToParticipants(tripID);
        return TripResponse.fromEntity(trip);
    }

    public ParticipantResponse inviteParticipantToTrip(UUID tripID, String emailToInvite) {
        Trip trip = getTripEntity(tripID);
        var response = this.participantService.registerParticipantForEvent(emailToInvite, trip);

        if (!trip.getIsConfirmed()) {
            throw new TripNotConfirmedException("The trip is not confirmed");
        }

        this.participantService.triggerConfirmationEmailToParticipant(emailToInvite);
        
        return response;
    }

    public ActivityCreatedResponse createActivity(UUID tripID, ActivityRequest request) {
        Trip trip = getTripEntity(tripID);

        return activityService.createActivity(trip, request);
    }

    public LinkCreatedResponse createLink(UUID tripID, LinkRequest request) {
        Trip trip = getTripEntity(tripID);

        return linkService.createLink(trip, request);
    }
}