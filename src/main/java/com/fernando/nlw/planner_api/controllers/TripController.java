package com.fernando.nlw.planner_api.controllers;

import com.fernando.nlw.planner_api.requests.ActivityRequest;
import com.fernando.nlw.planner_api.requests.LinkRequest;
import com.fernando.nlw.planner_api.requests.ParticipantRequest;
import com.fernando.nlw.planner_api.requests.TripRequest;
import com.fernando.nlw.planner_api.requests.TripUpdateRequest;
import com.fernando.nlw.planner_api.responses.*;
import com.fernando.nlw.planner_api.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import com.fernando.nlw.planner_api.services.ActivityService;
import com.fernando.nlw.planner_api.services.LinkService;
import com.fernando.nlw.planner_api.services.ParticipantService;
 
@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
    private final ParticipantService participantService;
    private final ActivityService activityService;
    private final LinkService linkService;

    // Trips

    @GetMapping("/{tripID}")
    public ResponseEntity<TripResponse> getTripDetail(@PathVariable UUID tripID) {
        return ResponseEntity.ok(tripService.findTripByID(tripID));
    }

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripRequest tripRequest) {
        TripResponse newTrip = tripService.registerTrip(tripRequest);
        return ResponseEntity.created(null).body(newTrip);
    }

    @PatchMapping("/{tripID}")
    public ResponseEntity<TripResponse> updateTrip(
        @PathVariable UUID tripID,
        @RequestBody TripUpdateRequest tripRequest) {
            TripResponse trip = tripService.updateTrip(tripID, tripRequest);
            return ResponseEntity.ok(trip);
    }

    @PutMapping("/{tripID}/confirm")
    public ResponseEntity<TripResponse> confirmTrip(@PathVariable UUID tripID) {
            TripResponse trip = tripService.confirmTrip(tripID);
            return ResponseEntity.ok(trip);
    }


    // Activities

    @PostMapping("/{tripID}/activities")
    public ResponseEntity<ActivityResponse> createActivity(
        @PathVariable UUID tripID,
        @RequestBody ActivityRequest request) {
        return ResponseEntity.ok(tripService.createActivity(tripID, request));
    }

    @GetMapping("/{tripID}/activities")
    public ResponseEntity<ActivitiesResponse> findAllActivities(
        @PathVariable UUID tripID) {
        return ResponseEntity.ok(activityService.findAllActivitiesByTripId(tripID));
    }


    // Links

    @PostMapping("/{tripID}/links")
    public ResponseEntity<LinkResponse> createLink(
        @PathVariable UUID tripID,
        @RequestBody LinkRequest request) {
        return ResponseEntity.ok(tripService.createLink(tripID, request));
    }

    @GetMapping("/{tripID}/links")
    public ResponseEntity<LinksResponse> findAllLinks(
        @PathVariable UUID tripID) {
        return ResponseEntity.ok(linkService.findAllLinksByTripId(tripID));
    }


    // Participants

    @GetMapping("/{tripID}/participants")
    public ResponseEntity<ParticipantsResponse> getAllParticipants(@PathVariable UUID tripID) {
        return ResponseEntity.ok(participantService.findAllParticipantsByTripID(tripID));
    }

    @PostMapping("/{tripID}/invite")
    public ResponseEntity<ParticipantResponse> inviteParticipant(
        @PathVariable UUID tripID, 
        @RequestBody ParticipantRequest request) {
            var response = tripService.inviteParticipantToTrip(tripID, request.email());
            return ResponseEntity.created(null).body(response);
    }
}