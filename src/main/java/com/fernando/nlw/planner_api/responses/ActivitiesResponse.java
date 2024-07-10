package com.fernando.nlw.planner_api.responses;

import java.util.UUID;
import lombok.Builder;
import java.util.List;

@Builder
public record ActivitiesResponse(
    UUID tripID,
    List<ActivityResponse> activities) {

        @Builder
        public record ActivityResponse(
            UUID id,
            String title,
            String accursAt,
            Boolean isFinish) {}
    }