package com.fernando.nlw.planner_api.controllers;

import com.fernando.nlw.planner_api.requests.ParticipantRequest;
import com.fernando.nlw.planner_api.services.ParticipantService;
import com.fernando.nlw.planner_api.responses.ParticipantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
@RequiredArgsConstructor
public class ParticipantController {
    private final ParticipantService participantService;


    @PutMapping("/{participantID}/confirm")
    public ResponseEntity<ParticipantResponse> confirmParticipant(@PathVariable UUID participantID, @RequestBody ParticipantRequest request) {
        var participant = participantService.confirmParticipant(participantID, request);
        return ResponseEntity.ok(participant);
    }

    @DeleteMapping("/{participantID}")
    public ResponseEntity<?> deleteParticipant(@PathVariable UUID participantID) {
        participantService.deleteParticipant(participantID);
        return ResponseEntity.noContent().build();
    }
}