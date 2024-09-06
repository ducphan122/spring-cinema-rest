package com.example.cinema.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.example.cinema.model.Cinema;
import com.example.cinema.model.Seat;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CinemaController {

    @GetMapping(value = "/seats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cinema getCinemaInfo() {
        int rows = 9;
        int columns = 9;
        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                seats.add(new Seat(row, col, true));
            }
        }

        return new Cinema(rows, columns, seats);
    }
}