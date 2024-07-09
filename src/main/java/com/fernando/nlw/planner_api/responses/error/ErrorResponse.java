package com.fernando.nlw.planner_api.responses.error;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String message,
        Integer statusCode,
        String path) {}
