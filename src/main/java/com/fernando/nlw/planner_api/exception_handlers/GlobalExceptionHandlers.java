package com.fernando.nlw.planner_api.exception_handlers;

import com.fernando.nlw.planner_api.exceptions.EntityNotFoundException;
import com.fernando.nlw.planner_api.exceptions.TripNotConfirmedException;
import com.fernando.nlw.planner_api.responses.error.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlers {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException exception,
            HttpServletRequest request) {

        Integer statusCode = HttpStatus.NOT_FOUND.value();
        String path = request.getRequestURI();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .path(path)
                .statusCode(statusCode)
                .build();

        return new ResponseEntity<>(
                errorResponse,
                HttpStatusCode.valueOf(statusCode)
        );
    }

    @ExceptionHandler(TripNotConfirmedException.class)
    public ResponseEntity<ErrorResponse> handleTripNotConfirmed(
            TripNotConfirmedException exception,
            HttpServletRequest request) {

        Integer statusCode = HttpStatus.BAD_REQUEST.value();
        String path = request.getRequestURI();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(exception.getMessage())
                .path(path)
                .statusCode(statusCode)
                .build();

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
