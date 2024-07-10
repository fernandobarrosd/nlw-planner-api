package com.fernando.nlw.planner_api.responses;

import java.util.UUID;
import lombok.Builder;

@Builder
public record LinkCreatedResponse(
    UUID id,
    UUID tripID,
    String title,
    String url) {}