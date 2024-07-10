package com.fernando.nlw.planner_api.responses;

import java.util.UUID;
import lombok.Builder;

@Builder
public record ActivityCreatedResponse(
    UUID id,
    UUID tripID,
    String title,
    String accursAt,
    Boolean isFinish) {}