package com.fernando.nlw.planner_api.repositories;

import com.fernando.nlw.planner_api.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
}