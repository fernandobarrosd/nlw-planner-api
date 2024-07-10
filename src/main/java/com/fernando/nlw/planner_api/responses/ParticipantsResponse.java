package com.fernando.nlw.planner_api.responses;

import java.util.UUID;
import java.util.List;
import lombok.Builder;

@Builder
public record ParticipantsResponse(
    UUID tripID,
    List<ParticipantResponse> participants) {

        @Builder
        public record ParticipantResponse(
            UUID id,
            String name,
            Boolean isConfirmed,
            String email) {

        }
    }