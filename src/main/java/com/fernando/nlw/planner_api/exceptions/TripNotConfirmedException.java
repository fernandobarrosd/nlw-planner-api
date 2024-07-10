package com.fernando.nlw.planner_api.exceptions;

public class TripNotConfirmedException extends RuntimeException {
    public TripNotConfirmedException(String message) {
        super(message);
    }
}
