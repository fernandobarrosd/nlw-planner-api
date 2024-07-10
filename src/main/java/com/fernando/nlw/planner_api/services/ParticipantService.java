package com.fernando.nlw.planner_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.fernando.nlw.planner_api.exceptions.EntityNotFoundException;
import com.fernando.nlw.planner_api.models.Participant;
import com.fernando.nlw.planner_api.models.Trip;
import com.fernando.nlw.planner_api.repositories.ParticipantRepository;
import com.fernando.nlw.planner_api.responses.ParticipantResponse;
import com.fernando.nlw.planner_api.responses.ParticipantsResponse;
import com.fernando.nlw.planner_api.requests.ParticipantRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fernando.nlw.planner_api.repositories.TripRepository;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    private final TripRepository tripRepository;

    public void registerParticipantForEvent(List<String> participantsToInvite, Trip trip) {
        List<Participant> participants = participantsToInvite
                .stream()
                .map(email -> new Participant(email, trip))
                .toList();

        this.participantRepository.saveAll(participants);
    }

    public ParticipantResponse registerParticipantForEvent(String participantToInvite, Trip trip) {
        Participant participant = new Participant(participantToInvite, trip);
        this.participantRepository.save(participant);

        return ParticipantResponse.builder()
            .id(participant.getId())
            .email(participantToInvite)
            .tripID(trip.getId())
            .build();
    }

    public void deleteParticipant(UUID participantID) {
        Optional<Participant> participantOptional = participantRepository.findById(participantID);
        if (participantOptional.isEmpty()) {
            throw new EntityNotFoundException("The participant#%s is not exists".formatted(participantID));
        }

        participantRepository.deleteById(participantID);
    }

    public ParticipantsResponse findAllParticipantsByTripID(UUID tripID) {
        if (tripRepository.findById(tripID).isEmpty()) {
            throw new EntityNotFoundException("The trip#%s is not exists".formatted(tripID));
        }

        var participants = this.participantRepository.findByTripId(tripID)
            .stream()
            .map(participant -> ParticipantResponse.builder()
                .id(participant.getId())
                .isConfirmed(participant.getIsConfirmed())
                .name(participant.getName())
                .email(participant.getEmail())
                .build())
            .toList();
        return ParticipantsResponse.builder()
                .tripID(tripID)
                .participants(participants)
                .build();
            
    }

    public void triggerConfirmationEmailToParticipants(UUID tripID) {}

    public ParticipantResponse confirmParticipant(UUID participantID, ParticipantRequest request) {
        Optional<Participant> participantOptional = participantRepository.findById(participantID);
        if (participantOptional.isEmpty()) {
            throw new EntityNotFoundException("The participant#%s is not exists".formatted(participantID));
        }
        Participant participant = participantOptional.get();
        participant.setName(request.name());
        participant.setEmail(request.email());
        participant.setIsConfirmed(true);
        this.participantRepository.save(participant);

        return ParticipantResponse.builder()
            .id(participantID)
            .tripID(participant.getTrip().getId())
            .isConfirmed(participant.getIsConfirmed())
            .name(request.name())
            .email(request.email())
            .build();
    }

    public void triggerConfirmationEmailToParticipant(String emailToInvite) {}
}