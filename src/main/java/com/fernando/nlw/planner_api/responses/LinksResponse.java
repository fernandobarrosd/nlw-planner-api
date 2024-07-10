package com.fernando.nlw.planner_api.responses;

import java.util.UUID;
import lombok.Builder;
import java.util.List;

@Builder
public record LinksResponse(
    UUID tripID,
    List<LinkResponse> links) {

        @Builder
        public record LinkResponse(
            UUID id,
            String title,
            String url) {}
    }