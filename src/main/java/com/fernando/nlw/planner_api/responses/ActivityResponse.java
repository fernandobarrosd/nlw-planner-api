package com.fernando.nlw.planner_api.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.UUID;

@Builder
public record ActivityResponse(
        UUID id,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        UUID tripID,
        String title,
        String accursAt,
        Boolean isFinish) {}