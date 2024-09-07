package com.example.cinema.model;

import lombok.Data;
import java.util.List;

import java.util.ArrayList;

@Data
public class Cinema {
    private int rows;
    private int columns;
    private List<Seat> seats;

    public Cinema(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        List<Seat> seats = new ArrayList<Seat>();
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                seats.add(new Seat(row, col, true));
            }
        }
        this.seats = seats;
    }

    public Seat getSeat(int row, int col) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getCol() == col)
                .findFirst()
                .orElse(null);
    }

}