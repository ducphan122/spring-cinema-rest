package com.example.cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Seat {
    private int row;
    @JsonProperty("column")
    private int col;
    private boolean available;

    public Seat(int row, int col, boolean available) {
        this.row = row;
        this.col = col;
        this.available = available;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String toString() {
        return "(" + row + "," + col + ")";
    }

}