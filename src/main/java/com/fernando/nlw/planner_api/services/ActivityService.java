package com.fernando.nlw.planner_api.services;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.fernando.nlw.planner_api.exceptions.EntityNotFoundException;
import com.fernando.nlw.planner_api.models.Activity;
import com.fernando.nlw.planner_api.models.Trip;
import com.fernando.nlw.planner_api.repositories.ActivityRepository;
import com.fernando.nlw.planner_api.repositories.TripRepository;
import com.fernando.nlw.planner_api.requests.ActivityRequest;
import com.fernando.nlw.planner_api.responses.ActivitiesResponse;
import com.fernando.nlw.planner_api.responses.ActivityCreatedResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ActivityService {
    private final ActivityRepository activityRepository;
    private final TripRepository tripRepository;

    public ActivityCreatedResponse createActivity(Trip trip, ActivityRequest request) {
        Activity activity = new Activity(trip, request);

        activityRepository.save(activity);

        return ActivityCreatedResponse.builder()
            .id(activity.getId())
            .isFinish(activity.getIsFinish())
            .title(activity.getTitle())
            .accursAt(activity.getAccursAt().toString())
            .tripID(trip.getId())
            .build();
    }

    public ActivitiesResponse findAllActivitiesByTripId(UUID tripID) {
        if (tripRepository.findById(tripID).isEmpty()) {
            throw new EntityNotFoundException("The trip#%s is not exists".formatted(tripID));
        }
        var activites = activityRepository.findByTripId(tripID)
            .stream()
            .map(activity -> {
                return ActivitiesResponse.ActivityResponse.builder()
                    .id(activity.getId())
                    .title(activity.getTitle())
                    .isFinish(activity.getIsFinish())
                    .accursAt(activity.getAccursAt().toString())
                    .build();
            })
            .toList();

        return ActivitiesResponse.builder()
            .tripID(tripID)
            .activities(activites)
            .build();
    }
}