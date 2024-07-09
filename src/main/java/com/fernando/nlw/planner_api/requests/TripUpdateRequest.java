package com.fernando.nlw.planner_api.requests;

public record TripUpdateRequest(
    String destination,
    String startsAt,
    String endsAt) {}