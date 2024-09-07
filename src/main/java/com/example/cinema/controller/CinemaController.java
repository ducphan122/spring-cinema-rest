package com.example.cinema.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.cinema.model.Cinema;
import com.example.cinema.model.Seat;

import java.util.Map;
import java.util.UUID;

import com.example.cinema.exception.CinemaException;
import com.example.cinema.exception.CinemaException.ErrorType;

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
        UUID token;
        Seat ticket;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class SeatReturnRequest {
        UUID token;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class StatsResponse {
        int current_income;
        int number_of_available_seats;
        int number_of_purchased_tickets;
    }

    private static final String OUT_OF_BOUNDS_ERROR = "The number of a row or a column is out of bounds!";
    private static final String ALREADY_PURCHASED_ERROR = "The ticket has been already purchased!";

    @GetMapping(value = "/seats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cinema getCinemaInfo() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseSeat(@RequestBody SeatPurchaseRequest request) {
        if (!cinema.isValidSeat(request.getRow(), request.getColumn())) {
            throw new CinemaException(OUT_OF_BOUNDS_ERROR, ErrorType.INVALID_SEAT);
        }

        Seat seat = cinema.purchaseSeat(request.getRow(), request.getColumn());
        if (seat == null) {
            throw new CinemaException(ALREADY_PURCHASED_ERROR, ErrorType.SEAT_ALREADY_PURCHASED);
        }

        UUID token = UUID.randomUUID();
        cinema.buyTicket(seat, token);
        return ResponseEntity.ok(new SeatPurchaseResponse(token, seat));
    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody SeatReturnRequest request) {
        Seat seat = cinema.returnTicket(request.getToken());
        if (seat != null) {
            return ResponseEntity.ok(Map.of("returned_ticket", seat));
        }
        throw new CinemaException("Wrong token!", ErrorType.INVALID_TOKEN);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(name = "password", required = false) String password) {
        if (password != null && password.equals("super_secret")) {
            StatsResponse stats = new StatsResponse(
                    cinema.getCurrentIncome(),
                    cinema.getAvailableSeats(),
                    cinema.getPurchasedTicketsCount());
            return ResponseEntity.ok(stats);
        }
        throw new CinemaException("The password is wrong!", ErrorType.INVALID_PASSWORD);
    }
}