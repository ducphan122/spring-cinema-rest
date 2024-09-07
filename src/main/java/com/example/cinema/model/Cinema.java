package com.example.cinema.model;

import lombok.Data;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class Cinema {
    private int rows;
    private int columns;
    private List<Seat> seats;
    @JsonIgnore
    private int currentIncome;
    @JsonIgnore
    private Set<Seat> purchasedSeats;
    @JsonIgnore
    private Map<UUID, Seat> purchasedTickets;

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
        this.purchasedSeats = new HashSet<>();
        this.purchasedTickets = new HashMap<>();
    }

    public Seat getSeat(int row, int col) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getCol() == col)
                .findFirst()
                .orElse(null);
    }

    public boolean isValidSeat(int row, int column) {
        return row > 0 && row <= rows && column > 0 && column <= columns;
    }

    public Seat purchaseSeat(int row, int column) {
        Seat seat = getSeat(row, column);
        if (seat != null && seat.isAvailable()) {
            seat.setAvailable(false);
            purchasedSeats.add(seat);
            currentIncome += seat.getPrice();
            return seat;
        }
        return null;
    }

    public Seat returnTicket(UUID token) {
        Seat seat = purchasedTickets.remove(token);
        if (seat != null) {
            seat.setAvailable(true);
            purchasedSeats.remove(seat);
            currentIncome -= seat.getPrice();
        }
        return seat;
    }

    public void buyTicket(Seat seat, UUID token) {
        purchasedSeats.add(seat);
        purchasedTickets.put(token, seat);
    }

    public int getAvailableSeats() {
        return seats.size() - purchasedSeats.size();
    }

    public int getPurchasedTicketsCount() {
        return purchasedTickets.size();
    }
}