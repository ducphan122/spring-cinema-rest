package com.example.cinema.exception;

public class CinemaException extends RuntimeException {
    public enum ErrorType {
        INVALID_SEAT,
        SEAT_ALREADY_PURCHASED,
        INVALID_TOKEN,
        INVALID_PASSWORD
    }

    private final ErrorType errorType;

    public CinemaException(String message, ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }
}