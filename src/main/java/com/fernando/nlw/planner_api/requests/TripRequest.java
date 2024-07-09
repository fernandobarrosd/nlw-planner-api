package com.fernando.nlw.planner_api.requests;

import java.util.List;

public record TripRequest(
        String destination,
        String startsAt,
        String endsAt,
        List<String> emailsToInvite,
        String ownerEmail,
        String ownerName) {}