package com.fernando.nlw.planner_api.controllers;

import com.fernando.nlw.planner_api.requests.TripRequest;
import com.fernando.nlw.planner_api.responses.TripResponse;
import com.fernando.nlw.planner_api.services.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {
    private final TripService tripService;

    @GetMapping("/{tripID}")
    public ResponseEntity<TripResponse> getTripDetail(@PathVariable UUID tripID) {
        return ResponseEntity.ok(tripService.findTripByID(tripID));
    }

    @PostMapping
    public ResponseEntity<TripResponse> createTrip(@RequestBody TripRequest tripRequest) {
        TripResponse newTrip = tripService.registerTrip(tripRequest);
        return ResponseEntity.created(null).body(newTrip);
    }
}