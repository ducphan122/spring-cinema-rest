package com.example.cinema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.cinema.model.Cinema;
import com.example.cinema.model.Seat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CinemaController {
    private final Cinema cinema = new Cinema(9, 9);

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class SeatPurchaseRequest {
        int row;
        int column;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class SeatPurchaseResponse {
        Seat ticket;
    }

    private static final String OUT_OF_BOUNDS_ERROR = "The number of a row or a column is out of bounds!";
    private static final String ALREADY_PURCHASED_ERROR = "The ticket has been already purchased!";

    @GetMapping(value = "/seats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cinema getCinemaInfo() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody SeatPurchaseRequest request) {
        if (!isValidSeat(request.getRow(), request.getColumn())) {
            return createErrorResponse(OUT_OF_BOUNDS_ERROR);
        }

        Seat seat = cinema.getSeat(request.getRow(), request.getColumn());
        if (!seat.isAvailable()) {
            return createErrorResponse(ALREADY_PURCHASED_ERROR);
        }

        seat.setAvailable(false);
        return ResponseEntity.ok(new SeatPurchaseResponse(seat));
    }

    private boolean isValidSeat(int row, int column) {
        return row > 0 && row <= cinema.getRows() && column > 0 && column <= cinema.getColumns();
    }

    private ResponseEntity<?> createErrorResponse(String errorMessage) {
        return ResponseEntity.badRequest().body(Map.of("error", errorMessage));
    }
}