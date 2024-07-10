package com.fernando.nlw.planner_api.repositories;

import com.fernando.nlw.planner_api.models.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    List<Participant> findByTripId(UUID id);    
}