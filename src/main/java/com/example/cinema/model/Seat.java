package com.example.cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Seat {
    private int row;
    @JsonProperty("column")
    private int col;
    private int price;
    private boolean available;

    public Seat(int row, int col, boolean available) {
        this.row = row;
        this.col = col;
        this.available = available;
        this.price = row <= 4 ? 10 : 8;
    }

    // Custom toString method (if you want to keep the specific format)
    @Override
    public String toString() {
        return "(" + row + "," + col + ")";
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

}