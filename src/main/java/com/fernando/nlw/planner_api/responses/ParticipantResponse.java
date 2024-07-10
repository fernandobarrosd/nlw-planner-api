package com.fernando.nlw.planner_api.responses;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record ParticipantResponse(
    UUID id,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    UUID tripID,
    String name,
    Boolean isConfirmed,
    String email) {}