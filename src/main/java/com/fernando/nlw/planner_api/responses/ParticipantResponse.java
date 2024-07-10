package com.fernando.nlw.planner_api.responses;

import java.util.UUID;
import lombok.Builder;

@Builder
public record ParticipantResponse(
    UUID id,
    UUID tripID,
    String name,
    Boolean isConfirmed,
    String email) {}