package com.fernando.nlw.planner_api.repositories;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fernando.nlw.planner_api.models.Link;

public interface LinkRepository extends JpaRepository<Link, UUID>{
    List<Link> findByTripId(UUID id);
}