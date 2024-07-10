package com.fernando.nlw.planner_api.exceptions;

public class TripEndsDateBiggerThanStartsAtException extends RuntimeException {
    public TripEndsDateBiggerThanStartsAtException(String message) {
        super(message);
    }
}