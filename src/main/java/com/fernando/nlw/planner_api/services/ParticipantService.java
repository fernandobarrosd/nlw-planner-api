package com.fernando.nlw.planner_api.services;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ParticipantService {
    public void registerParticipantForEvent(List<String> participantsToInvite, UUID tripID) {}

    public void triggerConfirmationEmailToParticipants(UUID tripID) {}
}