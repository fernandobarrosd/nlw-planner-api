package com.fernando.nlw.planner_api.controllers;

import com.fernando.nlw.planner_api.requests.ParticipantRequest;
import com.fernando.nlw.planner_api.requests.TripRequest;
import com.fernando.nlw.planner_api.requests.TripUpdateRequest;
import com.fernando.nlw.planner_api.responses.TripResponse;
import com.fernando.nlw.planner_api.responses.ParticipantResponse;
import com.fernando.nlw.planner_api.responses.ParticipantsResponse;
import com.fernando.nlw.planner_api.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import com.fernando.nlw.planner_api.services.ParticipantService;
 
@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;
    private final ParticipantService participantService;

    @GetMapping("/{tripID}")
    public ResponseEntity<TripResponse> getTripDetail(@PathVariable UUID tripID) {
        return ResponseEntity.ok(tripService.findTripByID(tripID));
    }

    @GetMapping("/{tripID}/participants")
    public ResponseEntity<ParticipantsResponse> getAllParticipants(@PathVariable UUID tripID) {
        return ResponseEntity.ok(participantService.findAllParticipantsByTripID(tripID));
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

    @PostMapping("/{tripID}/invite")
    public ResponseEntity<ParticipantResponse> inviteParticipant(
        @PathVariable UUID tripID, 
        @RequestBody ParticipantRequest request) {
            var response = tripService.inviteParticipantToTrip(tripID, request.email());
            return ResponseEntity.created(null).body(response);
    }
}